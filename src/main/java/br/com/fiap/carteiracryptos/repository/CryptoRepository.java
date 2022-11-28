package br.com.fiap.carteiracryptos.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.carteiracryptos.model.Crypto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
public class CryptoRepository implements PanacheRepository<Crypto> {
   
   @PersistenceContext
   EntityManager em;

   public List<Crypto> listarCryptos() throws SQLException{
      
      TypedQuery<Crypto> query = em.createNamedQuery("LISTAR_CRYPTOS", Crypto.class);

      try {
         return query.getResultList();
      } catch (NoResultException e) {
         System.out.println("Lista de cryptos vazia!");
         return new ArrayList<>();
      } catch (PersistenceException e) {
         throw new SQLException(e);
      }
   }

   public Crypto buscaCrypto(String codigo) throws SQLException {

      TypedQuery<Crypto> query = em.createNamedQuery("BUSCAR_CRYPTO", Crypto.class);
      query.setParameter("codigo", codigo);

      try {
         return query.getSingleResult();
      } catch (NoResultException e) {
         System.out.println("Não foi encontrada a crypto solictada");
         return null;
      } catch (PersistenceException e) {
         throw new SQLException(e);
      }
   }

   public Crypto inseriCrypto(Crypto crypto) throws SQLException{

      Query query = em.createNamedQuery("INSERIR_CRYPTO");
      query.setParameter("codigo", crypto.getCodigo());
      query.setParameter("nome", crypto.getNome());
      query.setParameter("valorCompra", crypto.getValorCompra());
      query.setParameter("valorVenda", crypto.getValorVenda());

      try {
         query.executeUpdate();
         return crypto;
      } catch (PersistenceException e) {
         System.out.println("Não foi possível inserir a crypto!");
         throw new SQLException(e);
      }
   }
   // TODO: update
   // TODO: Exclui
}
