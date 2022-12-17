package br.com.fiap.carteiracryptos.dto;

public class ClienteDTOupdate {
   
   Long id;
   String nome;
   
   // CONSTRUTORES
   public ClienteDTOupdate() {
   }
   public ClienteDTOupdate(Long id, String nome) {
      this.id = id;
      this.nome = nome;
   }

   // GETTERS AND SETTERS
   public String getNome() {
      return this.nome;
   }
   public Long getId(){
      return this.id;
   }
}
