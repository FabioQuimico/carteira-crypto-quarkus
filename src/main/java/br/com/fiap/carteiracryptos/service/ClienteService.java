package br.com.fiap.carteiracryptos.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import br.com.fiap.carteiracryptos.dto.ClienteDTO;
import br.com.fiap.carteiracryptos.dto.ClienteDTOupdate;
import br.com.fiap.carteiracryptos.dto.CryptoClienteDTO;
import br.com.fiap.carteiracryptos.model.Cliente;
import br.com.fiap.carteiracryptos.model.CryptoCliente;
import br.com.fiap.carteiracryptos.repository.ClienteRepository;

@RequestScoped
public class ClienteService {
   @Inject
   ClienteRepository repository;
   @Inject
   CryptoClienteService ccService;

   public List<Cliente> listarClientes(){
      try {
         return repository.listarClientes();
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }
   }

   public Cliente buscarCliente(Long id){
      return repository.find("id", id).singleResult();
   }

   @Transactional
   public Cliente inserirCliente(ClienteDTO clienteDTO) throws SQLException{
      Cliente cliente = new Cliente();
      cliente.setId(repository.count() +1);
      cliente.setNome(clienteDTO.getNome());
      // repository.persist(cliente);
      // return cliente;
      try {
         return repository.inserirCliente(cliente);
      } catch (Exception e) {
         System.out.println("O cliente não pôde ser inserido!");
         e.printStackTrace();
         return null;
      }
   }

   @Transactional
   public Cliente atualizarCliente(ClienteDTOupdate clienteCerto){
      Cliente cliente;
      try {
         cliente = repository.buscarCliente(clienteCerto.getId());
      } catch (SQLException e) {
         throw new NotFoundException();
      }

      cliente.setNome(clienteCerto.getNome());
      return cliente;
      
   }
   @Transactional
   public void excluirCliente(Long id){
      Cliente cliente;
      try {
         cliente = repository.buscarCliente(id);
         repository.delete(cliente);
      } catch (SQLException e) {
         throw new NotFoundException();
      }      
   }



   //* Transações com as criptomoedas do cliente */
   @Transactional
   public CryptoClienteDTO compraCrypto(CryptoClienteDTO compra) throws Exception {

      Cliente cliente = repository.buscarCliente(compra.getIdCliente());

      // Verifica se o cliente ja possui daquela crypto
      CryptoCliente cryptoPossuida = cliente.buscaCrypto(compra.getCodigoCrypto());
      if (cryptoPossuida != null) {
         //TODO: Testar e remover comentário
         System.out.println(" *** O CLIENTE JÁ POSSUI ESSA CRYPTO ***");
         cryptoPossuida.setQuantidade(cryptoPossuida.getQuantidade().add(compra.getQuantidade()));
         return ccService.saveCryptoCliente(cryptoPossuida).toDTO();
      } else {
         //TODO: Testar e remover comentário
         System.out.println(" *** O CLIENTE NÃO POSSUIA ESSA CRYPTO ***");
         return ccService.insereCryptoCliente(compra).toDTO();
      }
   }

   @Transactional
   public CryptoClienteDTO vendeCrypto(CryptoClienteDTO venda) throws Exception {

      Cliente cliente = repository.buscarCliente(venda.getIdCliente());
      CryptoCliente cryptoPossuida = cliente.buscaCrypto(venda.getCodigoCrypto());

      if (cryptoPossuida != null) {
         if (venda.getQuantidade().compareTo(cryptoPossuida.getQuantidade()) <= 0) {
            cryptoPossuida.setQuantidade(cryptoPossuida.getQuantidade().subtract(venda.getQuantidade()));
            ccService.saveCryptoCliente(cryptoPossuida);
            if (cryptoPossuida.getQuantidade().compareTo(BigDecimal.ZERO) == 0){
               ccService.deletaCryptoCliente(cryptoPossuida);
            }
         } else {
            throw new Exception("*** Cliente não possui essa quantidade da criptomoeda! ***");   
         }
      } else {
         throw new Exception("*** Cliente não possui essa criptomoeda ***");
      }
      return cryptoPossuida.toDTO();
   }

}