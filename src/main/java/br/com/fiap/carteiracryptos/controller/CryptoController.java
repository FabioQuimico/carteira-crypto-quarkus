package br.com.fiap.carteiracryptos.controller;

import br.com.fiap.carteiracryptos.model.Crypto;
import br.com.fiap.carteiracryptos.service.CryptoService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
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
      return Response.status(Response.Status.OK).entity(service.listarCryptos()).build();
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
      return Response.status(Response.Status.OK).entity(service.buscarCrypto(codigo)).build();
   }   
   
}
