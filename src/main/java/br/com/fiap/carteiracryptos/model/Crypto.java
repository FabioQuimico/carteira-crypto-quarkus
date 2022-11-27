package br.com.fiap.carteiracryptos.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import javax.persistence.NamedNativeQueries;

@Entity
@Table(name = "cryptos")
@NamedNativeQueries({
   @NamedNativeQuery(
      name="LISTAR_CRYPTOS",
      query="SELECT codigo, nome, valorCompra, valorVenda FROM cryptos ;",
      resultClass = Crypto.class
   ),
   @NamedNativeQuery(
      name="BUSCAR_CRYPTO",
      query="SELECT codigo, nome, valorCompra, valorVenda FROM cryptos WHERE codigo = :codigo ;", 
      resultClass = Crypto.class
   ),
   @NamedNativeQuery(
      name="INSERIR_CRYPTO",
      query="INSERT into cryptos(codigo, nome, valorCompra, valorVenda) VALUES (:codigo, :nome, :valorCompra,:valorVenda) ;"
   ),
   @NamedNativeQuery(
      name="ATUALIZAR_CRYPTO", 
      query = "UPDATE cryptos SET nome = :nome, valorCompra = :valorCompra, valorVenda = :valorVenda WHERE codigo = :codigo ;"
      ),
   @NamedNativeQuery(
      name = "EXCLUIR_CRYPTO",
      query = "DELETE cryptos where codigo=:codigo ;"
   )
})
public class Crypto {
   
   @Id
   private String codigo;

   private String nome;
   private Double valorCompra;
   private Double valorVenda;

   // Construtores
   public Crypto() {
   }
   public Crypto(String codigo, String nome, Double valorCompra, Double valorVenda) {
      this.codigo = codigo;
      this.nome = nome;
      this.valorCompra = valorCompra;
      this.valorVenda = valorVenda;
   }
   
   // Getters and Setters
   public String getCodigo() {
      return codigo;
   }
   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }
   public String getNome() {
      return nome;
   }
   public void setNome(String nome) {
      this.nome = nome;
   }
   public Double getValorCompra() {
      return valorCompra;
   }
   public void setValorCompra(Double valorCompra) {
      this.valorCompra = valorCompra;
   }
   public Double getValorVenda() {
      return valorVenda;
   }
   public void setValorVenda(Double valorVenda) {
      this.valorVenda = valorVenda;
   }

   
}
