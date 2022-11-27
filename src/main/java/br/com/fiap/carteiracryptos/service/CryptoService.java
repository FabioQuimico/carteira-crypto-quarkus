package br.com.fiap.carteiracryptos.service;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.fiap.carteiracryptos.model.Crypto;
import br.com.fiap.carteiracryptos.repository.CryptoRepository;

@RequestScoped
public class CryptoService {
   
   @Inject CryptoRepository repository;

   public List<Crypto> listarCryptos() {
      return repository.findAll().list();
      // try {
      //    return repository.listarCryptos();
      // } catch (SQLException e) {
      //    e.printStackTrace();
      //    System.out.println("Não foi possível listar as cryptos");
      //    return null;
      // }
   }

   public Crypto buscarCrypto(String codigo){
      try {
         return repository.buscarCrypto(codigo.toUpperCase());
      } catch (Exception e) {
         System.out.println("Crypto não pôde ser encontrada!");
         return null;
      }
   }

   public Crypto inseriCrypto(Crypto crypto){
      try {
         crypto.setCodigo(crypto.getCodigo().toUpperCase());
         return repository.inseriCrypto(crypto);
      } catch (SQLException e) {
         System.out.println("Crypto não pôde ser insereida!");
         e.printStackTrace();
         return null;
      }
   }
   // TODO: UPDATE
   // TODO: DELETE
}
