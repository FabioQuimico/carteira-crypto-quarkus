package br.com.fiap.carteiracryptos.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.fiap.carteiracryptos.dto.ClienteCotado;
import br.com.fiap.carteiracryptos.dto.ClienteDTO;
import br.com.fiap.carteiracryptos.dto.ClienteDTOupdate;
import br.com.fiap.carteiracryptos.dto.CryptoClienteDTO;
import br.com.fiap.carteiracryptos.dto.CryptoDTO;
import br.com.fiap.carteiracryptos.model.Cliente;
import br.com.fiap.carteiracryptos.model.Crypto;
import br.com.fiap.carteiracryptos.model.CryptoCliente;
import br.com.fiap.carteiracryptos.repository.ClienteRepository;

@RequestScoped
public class ClienteService {
   @Inject
   ClienteRepository repository;
   @Inject
   CryptoClienteService ccService;
   @RestClient
   @Inject
   CotacaoService cService;

   public List<Cliente> listarClientes() {
      List<Cliente> lista = repository.findAll().list();
      return lista;
   }

   public ClienteCotado buscarCliente(Long id) {

      Cliente cliente = repository.find("id", id).singleResult();
      ClienteCotado clienteFull = new ClienteCotado(cliente);

      if (cliente != null) {
         clienteFull.getCriptos().stream().forEach(c -> {
            buscaCotacao(c);
            clienteFull.add(c);
         });
      }

      return clienteFull;
   }

   @Transactional
   public Cliente inserirCliente(ClienteDTO clienteDTO) throws SQLException {
      Cliente cliente = new Cliente();
      cliente.setId(repository.count() + 1);
      cliente.setNome(clienteDTO.getNome());
      try {
         return repository.inserirCliente(cliente);
      } catch (Exception e) {
         System.out.println("O cliente não pôde ser inserido!");
         e.printStackTrace();
         return null;
      }
   }

   @Transactional
   public Cliente atualizarCliente(ClienteDTOupdate clienteCerto) {
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
   public void excluirCliente(Long id) {
      Cliente cliente;
      try {
         cliente = repository.buscarCliente(id);
         repository.delete(cliente);
      } catch (SQLException e) {
         throw new NotFoundException();
      }
   }

   // * Transações com as criptomoedas do cliente */
   @Transactional
   public CryptoClienteDTO compraCrypto(CryptoClienteDTO compra) throws Exception {

      Cliente cliente = repository.buscarCliente(compra.getIdCliente());

      // Verifica se o cliente ja possui daquela crypto
      CryptoCliente cryptoPossuida = cliente.buscaCrypto(compra.getCodigoCrypto());
      if (cryptoPossuida != null) {
         cryptoPossuida.setQuantidade(cryptoPossuida.getQuantidade().add(compra.getQuantidade()));
         return ccService.saveCryptoCliente(cryptoPossuida).toDTO();
      } else {
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
            if (cryptoPossuida.getQuantidade().compareTo(BigDecimal.ZERO) == 0) {
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

   // * METODOS ACESSORIOS */
   Crypto buscaCotacao(Crypto crypto) {
      List<CryptoDTO> listaAtualizada = cService.getAList();

      Optional<CryptoDTO> cryptoDTO = 
         listaAtualizada.stream()
            .filter((CryptoDTO cd) -> cd.getCodigo().equalsIgnoreCase(crypto.getCodigo()))
            .findFirst();

      if (cryptoDTO != null) {
         System.out.println("*** CRYPTO ENCONTRADA!");
         System.out.println("Crypto encontrada: " +cryptoDTO);
         CryptoDTO cryptoEcontrada = cryptoDTO.get();
         crypto.setNome(cryptoEcontrada.getNome());
         crypto.setValorCompra(cryptoEcontrada.getCotacao_compra());
         crypto.setValorVenda(cryptoEcontrada.getCotacao_venda());
      } else {
         crypto.setNome("Criptomoeda nao encontrada");
         crypto.setValorCompra(0d);
         crypto.setValorVenda(0d);
      }
      System.out.println("Crypto montada: " +crypto);
      return crypto;
   }

}