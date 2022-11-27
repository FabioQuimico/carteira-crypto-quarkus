package br.com.fiap.carteiracryptos.service;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import br.com.fiap.carteiracryptos.model.Cliente;
import br.com.fiap.carteiracryptos.repository.ClienteRepository;

@RequestScoped
public class ClienteService {
   @Inject
   ClienteRepository respository;

   public List<Cliente> listarClientes(){
      try {
         return respository.listarClientes();
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }
   }

   public Cliente buscarCliente(Long id) throws SQLException{
      try {
         return respository.buscarCliente(id);
      } catch (NoResultException e) {
         System.out.println("O cliente procurado nao existe!");
         return null;
      } catch (SQLException e) {
         System.out.println("Erro na busca SQL");
         e.printStackTrace();
         throw new SQLException(e);
      }
   }

   public Cliente inserirCliente(Cliente cliente){

      try {
         // System.out.println("Service inserindo: " +cliente);
         cliente.setId(respository.count() +1);
         return respository.inserirCliente(cliente);
      } catch (Exception e) {
         System.out.println("O cliente não pôde ser inserido!");
         e.printStackTrace();
         return null;
      }
   }

//TODO: Atualizar cliente
//TODO: Excluir cliente


}
