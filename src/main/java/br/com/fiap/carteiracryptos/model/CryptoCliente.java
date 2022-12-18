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

@Entity
@Table(name="crypto_cliente")
//TODO: Testar e retirar namedQuery
@NamedNativeQueries({
   @NamedNativeQuery(
      name="BUSCA_CRYPTO_CLIENTE",
      query="SELECT idCliente, codCrypto, quantidade FROM crypto_cliente WHERE idCliente = :idCliente ;",
      resultClass = CryptoCliente.class
   )})
public class CryptoCliente implements Serializable {
   
   @Id
   @JsonBackReference
   @ManyToOne
   @JoinColumn(name = "idCliente", referencedColumnName = "id", nullable = false)
   Cliente cliente;

   @Id
   String codigoCrypto;

   BigDecimal quantidade;

   public CryptoCliente(){}

   public CryptoCliente(String codigoCrypto, BigDecimal quantidade){
      this.codigoCrypto = codigoCrypto;
      this.quantidade = quantidade;
   }

   public Cliente getCliente() {
      return cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public BigDecimal getQuantidade() {
      return quantidade;
   }

   public void setQuantidade(BigDecimal quantidade) {
      this.quantidade = quantidade;
   };

   public String getCodigo() {
      return this.codigoCrypto;
   }

   public void setCodCrypto(String codCrypto) {
      this.codigoCrypto = codCrypto;
   }
   
   public CryptoClienteDTO toDTO(){
      CryptoClienteDTO ccDTO = new CryptoClienteDTO();
      ccDTO.setCodigoCrypto(this.getCodigo());
      ccDTO.setIdCliente(this.cliente.getId());
      ccDTO.setQuantidade(this.quantidade);
      return ccDTO;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
      result = prime * result + ((codigoCrypto == null) ? 0 : codigoCrypto.hashCode());
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
      if (codigoCrypto == null) {
         if (other.codigoCrypto != null)
            return false;
      } else if (!codigoCrypto.equals(other.codigoCrypto))
         return false;
      return true;
   }

   @Override
   public String toString() {
      
      return "Cliente: " + this.cliente.getNome() + 
            " - Moeda: " + this.getCodigo() +
            " - Quantidade: " + this.quantidade;
   }
   
}
