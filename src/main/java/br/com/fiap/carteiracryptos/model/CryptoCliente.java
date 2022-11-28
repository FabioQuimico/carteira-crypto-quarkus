package br.com.fiap.carteiracryptos.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name="crypto_cliente")
@NamedNativeQueries({
   @NamedNativeQuery(
      name="BUSCA_CRYPTO_CLIENTE",
      query="SELECT id, idCliente, codigoCrypto, quantidade FROM crypto_cliente WHERE idCliente = :idCliente ;",
      resultClass = Crypto.class
   )})
public class CryptoCliente extends PanacheEntity {
   
   @JsonBackReference
   @ManyToOne
   @JoinColumn(name = "idCliente", referencedColumnName = "id", nullable = false)
   Cliente cliente;

   @ManyToOne
   @JoinColumn(name = "codigoCrypto", referencedColumnName = "codigo", nullable = false)
   Crypto crypto;

   BigDecimal quantidade;

   public CryptoCliente(){}

   public Cliente getCliente() {
      return cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Crypto getCrypto() {
      return crypto;
   }

   public void setCrypto(Crypto crypto) {
      this.crypto = crypto;
   }

   public BigDecimal getQuantidade() {
      return quantidade;
   }

   public void setQuantidade(BigDecimal quantidade) {
      this.quantidade = quantidade;
   };


   

}
