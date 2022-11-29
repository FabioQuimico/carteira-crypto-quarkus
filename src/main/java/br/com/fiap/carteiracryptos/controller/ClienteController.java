package br.com.fiap.carteiracryptos.controller;

import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import br.com.fiap.carteiracryptos.dto.ClienteDTO;
import br.com.fiap.carteiracryptos.dto.ClienteDTOupdate;
import br.com.fiap.carteiracryptos.dto.CryptoClienteDTO;
import br.com.fiap.carteiracryptos.model.Cliente;
import br.com.fiap.carteiracryptos.service.ClienteService;

@RequestScoped
@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {
   
   @Inject
   ClienteService service;

   @GET
   @Path("/lista")
   @Operation(
      summary = "Listar Clientes",
      description = "Retorna toda a lista de clientes")
   @APIResponse(
      responseCode = "200", 
      description = "Lista de clientes recuperada", 
      content = {
         @Content(
            mediaType = "application/json",
            schema = @Schema(
               implementation = Cliente.class
            )
         )
      }
   )
   public Response listarClientes() {
      return Response.status(Response.Status.OK).entity(service.listarClientes()).build();
   }

   @GET
   @Path("/{id}")
   @Operation(
      summary = "Busca um Cliente pelo Id",
      description = "Retorna o cliente que possui o Id informado")
   @APIResponse(
      responseCode = "302", 
      description = "Cliente recuperado", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = Cliente.class
            )
         )
      }
   )
   public Response buscarCliente(@PathParam("id") Long id){

      return Response.status(Response.Status.FOUND).entity(service.buscarCliente(id)).build();
   }

   @POST
   @Path("")
   @Operation(
      summary = "Inserir Cliente",
      description = "Insere o cliente informado no formato JSON")
   @APIResponse(
      responseCode = "201", 
      description = "Cliente inserido", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = Cliente.class,
               type = SchemaType.ARRAY
            )
         )
      }
   )
   public Response inserirCliente(@RequestBody ClienteDTO clienteDTO) throws SQLException{
      return Response.status(Response.Status.CREATED).entity(service.inserirCliente(clienteDTO)).build();
   }

   @PATCH
   @Path("")
   @Operation(
      summary = "Atualizar Cliente",
      description = "Altera o cliente informado no formato JSON")
   @APIResponse(
      responseCode = "202", 
      description = "Cliente Atualizado", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = Cliente.class,
               type = SchemaType.ARRAY
            )
         )
      }
   )
   public Response atualizarCliente(@RequestBody ClienteDTOupdate clienteUpdate){
      return Response.status(Response.Status.CREATED).entity(service.atualizarCliente(clienteUpdate)).build();
   }

   @POST
   @Path("/compra")
   @Operation(
      summary = "Compra Crypto para um Cliente",
      description = "Efetua a compra de uma crypto para um cliente informado no formato JSON")
   @APIResponse(
      responseCode = "202", 
      description = "Compra realizada", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = CryptoClienteDTO.class
            )
         )
      }
   )
   public Response compraCrypto(@RequestBody CryptoClienteDTO cryptoClienteDTO) throws Exception{
      return Response.status(Response.Status.ACCEPTED).entity(service.compraCrypto(cryptoClienteDTO)).build();
   }

   @POST
   @Path("/venda")
   @Operation(
      summary = "Vende Crypto de um Cliente",
      description = "Efetua a venda de uma crypto de um cliente informado no formato JSON")
   @APIResponse(
      responseCode = "202", 
      description = "Venda realizada", 
      content = {
         @Content(
            mediaType = "application/json", 
            schema = @Schema(
               implementation = CryptoClienteDTO.class
            )
         )
      }
   )
   public Response vendeCrypto(@RequestBody CryptoClienteDTO cryptoClienteDTO) throws Exception{
      return Response.status(Response.Status.ACCEPTED).entity(service.vendeCrypto(cryptoClienteDTO)).build();
   }
}
