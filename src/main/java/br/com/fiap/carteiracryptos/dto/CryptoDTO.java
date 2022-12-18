package br.com.fiap.carteiracryptos.dto;

import java.io.Serializable;

public class CryptoDTO implements Serializable {

   String id;
   String codigo;
   String nome;
   String descricao;
   double cotacao_compra;
   double cotacao_venda;
   String timestamp_atualizacao;

   // Constructors
   public CryptoDTO() {
   }
   public CryptoDTO(String id, String codigo, String nome, String descricao, double cotacao_compra,
         double cotacao_venda, String timestamp_atualizacao) {
      this.id = id;
      this.codigo = codigo;
      this.nome = nome;
      this.descricao = descricao;
      this.cotacao_compra = cotacao_compra;
      this.cotacao_venda = cotacao_venda;
      this.timestamp_atualizacao = timestamp_atualizacao;
   }

   // Getters and Setters
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
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
   public String getDescricao() {
      return descricao;
   }
   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }
   public double getCotacao_compra() {
      return cotacao_compra;
   }
   public void setCotacao_compra(double cotacao_compra) {
      this.cotacao_compra = cotacao_compra;
   }
   public double getCotacao_venda() {
      return cotacao_venda;
   }
   public void setCotacao_venda(double cotacao_venda) {
      this.cotacao_venda = cotacao_venda;
   }
   public String getTimestamp_atualizacao() {
      return timestamp_atualizacao;
   }
   public void setTimestamp_atualizacao(String timestamp_atualizacao) {
      this.timestamp_atualizacao = timestamp_atualizacao;
   }

   // Overrides
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
      CryptoDTO other = (CryptoDTO) obj;
      if (codigo == null) {
         if (other.codigo != null)
            return false;
      } else if (!codigo.equals(other.codigo))
         return false;
      return true;
   }
   @Override
   public String toString() {
      return "CryptoDTO [id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao
            + ", cotacao_compra=" + cotacao_compra + ", cotacao_venda=" + cotacao_venda + ", timestamp_atualizacao="
            + timestamp_atualizacao + "]";
   }
   

}
