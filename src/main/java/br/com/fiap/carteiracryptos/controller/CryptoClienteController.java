package br.com.fiap.carteiracryptos.controller;

import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import br.com.fiap.carteiracryptos.dto.CryptoClienteDTO;
import br.com.fiap.carteiracryptos.model.CryptoCliente;
import br.com.fiap.carteiracryptos.service.CryptoClienteService;


@RequestScoped
@Path("/cryptocliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CryptoClienteController {
   
   @Inject
   CryptoClienteService service;

   @GET
   @Path("/lista")
   @Operation(
      summary = "Listar Cryptos dos Clientes",
      description = "Retorna toda a lista de criptomoedas de todos os clientes do repositorio local")
   @APIResponse(
      responseCode = "200", 
      description = "Lista de cryptos recuperada", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = CryptoCliente.class, 
               type = SchemaType.ARRAY
            )
         )
      }
   )
   public Response listaCryptoClientes() {
      return Response.status(Response.Status.OK).entity(service.listaCryptoClientes()).build();
   }   

   @GET
   @Path("/{idCliente}")
   @Operation(
      summary = "Listar Cryptos de um Cliente",
      description = "Retorna toda a lista de criptomoedas de um cliente com id informado")
   @APIResponse(
      responseCode = "200", 
      description = "Lista de cryptos do cliente recuperada", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = Integer.class, 
               type = SchemaType.ARRAY
            )
         )
      }
   )
   public Response buscaCryptoCliente(@PathParam("idCliente") Long idCliente) throws SQLException {
      return Response.status(Response.Status.OK).entity(service.buscaCryptoCliente(idCliente)).build();
   } 

   @POST
   @Path("")
   @Operation(
      summary = "Inserir CryptoCliente",
      description = "Insere a Crypto informada (json) na base do cliente")
   @APIResponse(
      responseCode = "201", 
      description = "Crypto inserida", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = CryptoClienteDTO.class
            )
         )
      }
   )
   public Response inserirCrypto(@RequestBody CryptoClienteDTO cryptoClienteDTO) throws SQLException{
      return Response.status(Response.Status.CREATED).entity(service.insereCryptoCliente(cryptoClienteDTO)).build();
   } 
}
