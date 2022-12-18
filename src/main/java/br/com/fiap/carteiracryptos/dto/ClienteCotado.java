package br.com.fiap.carteiracryptos.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.fiap.carteiracryptos.model.Cliente;
import br.com.fiap.carteiracryptos.model.Crypto;

public class ClienteCotado {
   
   private String nome;

   Set<Crypto> criptos = new HashSet<Crypto>();

   public ClienteCotado(){};
   public ClienteCotado(Cliente cliente){
      this.nome = cliente.getNome();
      System.out.println("*** O cliente tem " +cliente.getCryptos().size()+ " cryptos diferentes");
      cliente.getCryptos().stream().forEach(c -> {
         Crypto crypto = new Crypto(c.getCodigo(), c.getQuantidade());
         criptos.add(crypto);
      });
   }

   public void setNome(String nome) {
      this.nome = nome;
   }
   public void setCriptos(Set<Crypto> criptos) {
      this.criptos = criptos;
   }
   public String getNome() {
      return nome;
   }
   public Set<Crypto> getCriptos() {
      return criptos;
   }
   public void add(Crypto crypto){
      this.criptos.add(crypto);
   }
   @Override
   public String toString() {
      String retorno = "Nome: " +nome+ " Criptos:";
      for (Crypto crypto : this.criptos) {
         retorno += " " + crypto.getCodigo();
      }
      return retorno;
   }

}
