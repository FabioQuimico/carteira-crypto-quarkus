package br.com.fiap.carteiracryptos.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.fiap.carteiracryptos.model.CryptoCliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
public class CryptoClienteRepository implements PanacheRepository<CryptoCliente>{

   EntityManager em;

   public List<CryptoCliente> buscaCryptoCliente(Long idCliente) throws SQLException {

      TypedQuery<CryptoCliente> query = em.createNamedQuery("BUSCA_CRYPTO_CLIENTE", CryptoCliente.class);
      query.setParameter("idCliente", idCliente);

         try {
         return query.getResultList();
      } catch (NoResultException e) {
         System.out.println("Lista de cryptos vazia!");
         return new ArrayList<>();
      } catch (PersistenceException e) {
         throw new SQLException(e);
      }
   }
   
}
