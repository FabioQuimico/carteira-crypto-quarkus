package br.com.fiap.carteiracryptos.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
      name = "ULTIMO_IDCLIENTE",
      query = "SELECT MAX(id) FROM clientes ;"
   )
})
public class Cliente extends PanacheEntity implements Serializable {
   private static final long serialVersionUID = 1L;

   @Column(nullable = false)
   private String nome;

   @OneToMany(
      mappedBy = "cliente",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL
   )
   @JsonManagedReference
   private Set<CryptoCliente> cryptos = new HashSet<CryptoCliente>();

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
   public Set<CryptoCliente> getCryptos(){
      return this.cryptos;
   }

   public CryptoCliente buscaCrypto(String codigo) {
      for (CryptoCliente cryptoCliente : cryptos) {
         if (cryptoCliente.getCodigo().equalsIgnoreCase(codigo))
            return cryptoCliente;
      }
      return null;
   }

   @Override
   public String toString() {
      String retorno = "ID: " +id+ " - Nome: " +nome+ " Criptos:";
      for (CryptoCliente crypto : cryptos) {
         retorno += " " + crypto.getCodigo();
      }
      return retorno;
   }
}
