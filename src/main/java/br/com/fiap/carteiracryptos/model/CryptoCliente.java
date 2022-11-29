package br.com.fiap.carteiracryptos.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.fiap.carteiracryptos.dto.CryptoClienteDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name="crypto_cliente")
@NamedNativeQueries({
   @NamedNativeQuery(
      name="BUSCA_CRYPTO_CLIENTE",
      query="SELECT id, idCliente, codigoCrypto, quantidade FROM crypto_cliente WHERE idCliente = :idCliente ;",
      resultClass = Crypto.class
   )})
public class CryptoCliente implements Serializable{
   
   @Id
   @JsonBackReference
   @ManyToOne
   @JoinColumn(name = "idCliente", referencedColumnName = "id", nullable = false)
   Cliente cliente;

   @Id
   @ManyToOne
   @JoinColumn(name = "codigoCrypto", referencedColumnName = "codigo", nullable = false)
   Crypto crypto;

   BigDecimal quantidade;

   public CryptoCliente(){}

   // public Long getId(){
   //    return this.id;
   // }
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

   public CryptoClienteDTO toDTO(){
      CryptoClienteDTO ccDTO = new CryptoClienteDTO();
      ccDTO.setCodigoCrypto(this.crypto.getCodigo());
      ccDTO.setIdCliente(this.cliente.getId());
      ccDTO.setQuantidade(this.quantidade);
      return ccDTO;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
      result = prime * result + ((crypto == null) ? 0 : crypto.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      CryptoCliente other = (CryptoCliente) obj;
      if (cliente == null) {
         if (other.cliente != null)
            return false;
      } else if (!cliente.equals(other.cliente))
         return false;
      if (crypto == null) {
         if (other.crypto != null)
            return false;
      } else if (!crypto.equals(other.crypto))
         return false;
      return true;
   }

   @Override
   public String toString() {
      
      return "Cliente: " + this.cliente.getNome() + 
            " - Moeda: " + this.crypto.getNome() +
            " - Quantidade: " + this.quantidade;
   }

   
}
