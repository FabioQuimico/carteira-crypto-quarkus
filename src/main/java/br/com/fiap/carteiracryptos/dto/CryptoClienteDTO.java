package br.com.fiap.carteiracryptos.dto;

import java.math.BigDecimal;

public class CryptoClienteDTO {
   
   Long idCliente;
   String codigoCrypto;
   BigDecimal quantidade;
   
   public CryptoClienteDTO(){};
   
   public CryptoClienteDTO(Long idCliente, String codigoCrypto, BigDecimal quantidade) {
      this.idCliente = idCliente;
      this.codigoCrypto = codigoCrypto;
      this.quantidade = quantidade;
   }

   public Long getIdCliente() {
      return idCliente;
   }
   public void setIdCliente(Long idCliente) {
      this.idCliente = idCliente;
   }
   public String getCodigoCrypto() {
      return codigoCrypto;
   }
   public void setCodigoCrypto(String codigoCrypto) {
      this.codigoCrypto = codigoCrypto;
   }
   public BigDecimal getQuantidade() {
      return quantidade;
   }
   public void setQuantidade(BigDecimal quantidade) {
      this.quantidade = quantidade;
   }

   
}
