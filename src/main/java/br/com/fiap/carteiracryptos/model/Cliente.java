package br.com.fiap.carteiracryptos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.NamedNativeQueries;

@Entity
@Table(name="clientes")
@NamedNativeQueries({
   @NamedNativeQuery(
      name="LISTAR_CLIENTES",
      query="SELECT id, nome FROM clientes ;",
      resultClass = Cliente.class
   ),
   @NamedNativeQuery(
      name="BUSCAR_CLIENTE",
      query="SELECT id, nome FROM clientes WHERE id = :id ;", 
      resultClass = Cliente.class
   ),
   @NamedNativeQuery(
      name="INSERIR_CLIENTE",
      query="INSERT into clientes(id, nome) VALUES (:id, :nome) ;"
   ),
   @NamedNativeQuery(
      name="ATUALIZAR_CLIENTE", 
      query = "UPDATE clientes SET nome = :nomeCliente WHERE id = :id ;"
      ),
   @NamedNativeQuery(
      name = "EXCLUIR_CLIENTE",
      query = "DELETE clientes where id=:idCliente ;"
   )
})
public class Cliente extends PanacheEntity implements Serializable {
   private static final long serialVersionUID = 1L;

   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // private Long id;

   @Column(nullable = false)
   private String nome;

   //Construtores
   public Cliente(){};
   public Cliente(Long id, String nome){
      this.id = id;
      this.nome = nome;
   }

   //Getters and Setters
   public Long getId() {
      return id;
   }
   public void setId(Long id){
      this.id = id;
   }
   public String getNome() {
      return nome;
   }
   public void setNome(String nome) {
      this.nome = nome;
   }

   @Override
   public String toString() {
      // System.out.println("ID: " +id+ " - Nome: " +nome);
      return ("ID: " +id+ " - Nome: " +nome);
   }
}
