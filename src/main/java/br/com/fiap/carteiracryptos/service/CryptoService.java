package br.com.fiap.carteiracryptos.service;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.fiap.carteiracryptos.model.Crypto;
import br.com.fiap.carteiracryptos.repository.CryptoRepository;

@RequestScoped
public class CryptoService {
   
   @Inject CryptoRepository repository;

   public List<Crypto> listaCryptos() {
      return repository.findAll().list();
      // try {
      //    return repository.listarCryptos();
      // } catch (SQLException e) {
      //    e.printStackTrace();
      //    System.out.println("Não foi possível listar as cryptos");
      //    return null;
      // }
   }

   public Crypto buscaCrypto(String codigo){
      try {
         return repository.buscaCrypto(codigo.toUpperCase());
      } catch (Exception e) {
         System.out.println("Crypto não pôde ser encontrada!");
         return null;
      }
   }

   @Transactional
   public Crypto saveCrypto(Crypto crypto){
      
      crypto.setCodigo(crypto.getCodigo().toUpperCase());
      repository.persist(crypto);
      return crypto;
      // try {
      //    return repository.inseriCrypto(crypto);
      // } catch (SQLException e) {
      //    System.out.println("Crypto não pôde ser inserida!");
      //    e.printStackTrace();
      //    return null;
      // }
   }

   @Transactional
   public void excluiCrypto(String codigo) throws SQLException{
      
      repository.delete(repository.buscaCrypto(codigo));
   }

}
