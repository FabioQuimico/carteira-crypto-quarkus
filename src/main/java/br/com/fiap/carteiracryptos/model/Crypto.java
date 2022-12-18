package br.com.fiap.carteiracryptos.model;

import java.math.BigDecimal;

public class Crypto {
   
   String codigo;
   String nome;
   BigDecimal quantidade;
   Double valorCompra;
   Double valorVenda;
   
   public Crypto(String codigo, BigDecimal quantidade) {
      this.codigo = codigo;
      this.quantidade = quantidade;
   }
   public Crypto() {
   }
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
   public BigDecimal getQuantidade() {
      return quantidade;
   }
   public void setQuantidade(BigDecimal quantidade) {
      this.quantidade = quantidade;
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
   @Override
   public String toString() {
      return "Crypto [codigo=" + codigo + ", nome=" + nome + ", quantidade=" + quantidade + ", valorCompra="
            + valorCompra + ", valorVenda=" + valorVenda + "]";
   }
   
}
