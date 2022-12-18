package br.com.fiap.carteiracryptos.repository;

import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.fiap.carteiracryptos.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
public class ClienteRepository implements PanacheRepository<Cliente>{

   @PersistenceContext
   EntityManager em;

   public Cliente buscarCliente(Long id) throws SQLException{
      TypedQuery<Cliente> query = em.createNamedQuery("BUSCAR_CLIENTE", Cliente.class);

      query.setParameter("id", id);

      try {
         return query.getSingleResult();
      } catch (NoResultException e) {
         return null;
      } catch (PersistenceException e) {
         throw new SQLException(e);
      }
   }

   public Long ultimoIdCliente() throws SQLException{
      TypedQuery<Long> query = em.createNamedQuery("ULTIMO_IDCLIENTE", Long.class);

      try {
         return query.getSingleResult();
      } catch (NoResultException e) {
         return null;
      } catch (PersistenceException e) {
         throw new SQLException(e);
      }
   }

   // Transactional e´necessário pra fazer o executeUpdate
   @Transactional
   public Cliente inserirCliente(Cliente cliente) throws SQLException{
      Query query = em.createNamedQuery("INSERIR_CLIENTE");
      // System.out.println("Respository inserindo: " +cliente);
      query.setParameter("id", cliente.getId());
      query.setParameter("nome", cliente.getNome());

      try {
         query.executeUpdate();
         return cliente;
      } catch (Exception e) {
         throw new SQLException(e);
      }
   }
   
}
