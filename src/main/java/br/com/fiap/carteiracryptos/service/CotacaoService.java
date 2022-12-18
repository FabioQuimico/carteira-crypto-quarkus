package br.com.fiap.carteiracryptos.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import br.com.fiap.carteiracryptos.dto.CryptoDTO;

@Path("/criptomoedas/")
@RegisterRestClient(configKey="extensions-api")
@Produces(MediaType.APPLICATION_JSON)
public interface CotacaoService {
   
   @GET
   List<CryptoDTO> getAList();

   @GET
   @Path("/{codigo}")
   CryptoDTO geCryptoDTO(@PathParam String codigo);
   
}
