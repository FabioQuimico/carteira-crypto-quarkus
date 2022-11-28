package br.com.fiap.carteiracryptos.service;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.fiap.carteiracryptos.dto.ClienteDTO;
import br.com.fiap.carteiracryptos.dto.CryptoClienteDTO;
import br.com.fiap.carteiracryptos.model.Cliente;
import br.com.fiap.carteiracryptos.model.Crypto;
import br.com.fiap.carteiracryptos.model.CryptoCliente;
import br.com.fiap.carteiracryptos.repository.ClienteRepository;
import br.com.fiap.carteiracryptos.repository.CryptoClienteRepository;

@RequestScoped
public class ClienteService {
   @Inject
   ClienteRepository repository;
   @Inject
   CryptoClienteService ccService;
   @Inject
   CryptoService cService;

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
      // try {
      //    return repository.buscarCliente(id);
      // } catch (NoResultException e) {
      //    System.out.println("O cliente procurado nao existe!");
      //    return null;
      // } catch (SQLException e) {
      //    System.out.println("Erro na busca SQL");
      //    e.printStackTrace();
      //    throw new SQLException(e);
      // }
   }

   @Transactional
   public Cliente inserirCliente(ClienteDTO clienteDTO){
      Cliente cliente = new Cliente();
      cliente.setId(repository.count() +1);
      cliente.setNome(clienteDTO.getNome());
      // System.out.println("*** Service inserindo: " +cliente);
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

//TODO: Atualizar cliente
//TODO: Excluir cliente

   public CryptoClienteDTO compraCrypto(CryptoClienteDTO compra) throws Exception {

      Cliente cliente = repository.buscarCliente(compra.getIdCliente());
      System.out.println("COMPRA: Cliente encontrado: " +cliente.getNome());

      // Verifica se o cliente ja possui daquela crypto
      CryptoCliente cryptoPossuida = cliente.buscaCrypto(compra.getCodigoCrypto());
      if (cryptoPossuida != null) {
         System.out.println(" *** O CLIENTE JÁ POSSUI ESSA CRYPTO ***");
         cryptoPossuida.setQuantidade(cryptoPossuida.getQuantidade().add(compra.getQuantidade()));
         return ccService.saveCryptoCliente(cryptoPossuida).toDTO();
      } else {
         System.out.println(" *** O CLIENTE NÃO POSSUIA ESSA CRYPTO ***");
         CryptoCliente cryptoCliente = new CryptoCliente();
         cryptoCliente.setCliente(cliente);

         // Verifica se a criptomoeda já existe na base
         Crypto crypto = cService.buscaCrypto(compra.getCodigoCrypto());

         if (crypto == null) {
            try {
            //TODO:   aService.atualizaCryptos();
               System.out.println("*** AGUARDE ATUALIZANDO BASE DE CRIPTOMOEDAS... ***");
            } catch (Exception e) {
               System.out.println("*** NÃO FOI POSSIVEL ATUALIZAR A BASE, USANDO DADOS LOCAIS ***");
            }
            crypto = cService.buscaCrypto(compra.getCodigoCrypto());
            // Tenta novamente com a base atualizada
            if (crypto == null) {
               throw new Exception("*** Criptomoeda não existe na base de dados ***");
      //       }
         } else {
            cryptoCliente.setCrypto(crypto);
            cryptoCliente.setQuantidade(compra.getQuantidade());
         }
         return ccService.saveCryptoCliente(cryptoCliente).toDTO();
         }
      return cryptoCliente.toDTO();
      }
   }
}