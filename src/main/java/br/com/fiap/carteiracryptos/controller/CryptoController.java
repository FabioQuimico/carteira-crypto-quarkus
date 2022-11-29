package br.com.fiap.carteiracryptos.controller;

import br.com.fiap.carteiracryptos.model.Crypto;
import br.com.fiap.carteiracryptos.service.CryptoService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/crypto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CryptoController {

   @Inject
   CryptoService service;

   @GET
   @Path("/lista")
   @Operation(
      summary = "Listar Cryptos",
      description = "Retorna toda a lista de criptomoedas do repositorio local")
   @APIResponse(
      responseCode = "200", 
      description = "Lista de cryptos recuperada", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = Crypto.class, 
               type = SchemaType.ARRAY
            )
         )
      }
   )
   public Response listarCryptos() {
      return Response.status(Response.Status.OK).entity(service.listaCryptos()).build();
   }   
   
   @GET
   @Path("/{codigo}")
   @Operation(
      summary = "Buscar Crypto",
      description = "Retorna a criptomoeda com o {codigo} informado do repositorio local")
   @APIResponse(
      responseCode = "200", 
      description = "Crypto recuperada", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = Crypto.class
            )
         )
      }
   )
   public Response buscarCrypto(@PathParam("codigo") String codigo) throws SQLException {
      return Response.status(Response.Status.OK).entity(service.buscaCrypto(codigo)).build();
   }   
   
   @POST
   @Path("")
   @Operation(
      summary = "Inserir Crypto",
      description = "Insere a Crypto informada (json) na base de dados")
   @APIResponse(
      responseCode = "201", 
      description = "Crypto inserida", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = Crypto.class
            )
         )
      }
   )
   public Response inserirCrypto(@RequestBody Crypto crypto){
      return Response.status(Response.Status.CREATED).entity(service.saveCrypto(crypto)).build();
   }   
   
   
   @DELETE
   @Path("/{codigo}")
   @Operation(
      summary = "Excluir Crypto",
      description = "Exclui a criptomoeda com o {codigo} informado do repositorio local")
   @APIResponse(
      responseCode = "200", 
      description = "Crypto excluida", 
      content = {
         @Content(
            mediaType = "application/json"
         )
      }
   )
   public Response excluirCrypto(@PathParam("codigo") String codigo) throws SQLException {
      service.excluiCrypto(codigo);
      return Response.status(Response.Status.OK).build();
   }   
   
}
