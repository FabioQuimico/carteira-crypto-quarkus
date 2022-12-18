package br.com.fiap.carteiracryptos;

import org.junit.jupiter.api.Test;

import br.com.fiap.carteiracryptos.dto.ClienteDTO;
import br.com.fiap.carteiracryptos.model.Cliente;
import br.com.fiap.carteiracryptos.service.ClienteService;
import br.com.fiap.carteiracryptos.service.CryptoClienteService;
import static org.hamcrest.CoreMatchers.containsString;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;

import javax.inject.Inject;

@QuarkusTest
public class CryptoClienteTest {

   @Inject
   ClienteService clService;
   @Inject
   CryptoClienteService ccService;

   Cliente cliente;
   
   @Test
   public void clienteDeveComprarCrypto() throws SQLException{
      ClienteDTO clienteDTO = new ClienteDTO("Astolfo");
      cliente = clService.inserirCliente(clienteDTO);
      
      given()
         .contentType(ContentType.JSON)
         .body("{\"idCliente\": 1,\"codigoCrypto\": \"ETH\",\"quantidade\": \"10\"}")
         .when()
         .post("/cliente/compra")
         .then().statusCode(202)
         .assertThat().body(containsString("ETH"));
   }
   
   @Test
   public void clienteDeveVenderCrypto() throws SQLException{
      ClienteDTO clienteDTO = new ClienteDTO("Astolfo");
      cliente = clService.inserirCliente(clienteDTO);
      given()
         .contentType(ContentType.JSON)
         .body("{\"idCliente\": 1,\"codigoCrypto\": \"ETH\",\"quantidade\": \"1\"}")
         .when()
         .post("/cliente/venda")
         .then().statusCode(202)
         .assertThat().body(containsString("\"quantidade\":9.0"));
   }
}
