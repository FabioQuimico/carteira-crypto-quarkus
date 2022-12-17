package br.com.fiap.carteiracryptos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.carteiracryptos.dto.ClienteDTO;
import br.com.fiap.carteiracryptos.dto.CryptoClienteDTO;
import br.com.fiap.carteiracryptos.model.Cliente;
import br.com.fiap.carteiracryptos.model.Crypto;
import br.com.fiap.carteiracryptos.service.ClienteService;
import br.com.fiap.carteiracryptos.service.CryptoClienteService;
import br.com.fiap.carteiracryptos.service.CryptoService;
import static org.hamcrest.CoreMatchers.containsString;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.inject.Inject;

@QuarkusTest
public class CryptoClienteTest {

   @Inject
   ClienteService clService;
   @Inject
   CryptoService crService;
   @Inject
   CryptoClienteService ccService;

   Cliente cliente;
   Crypto crypto;
   
   @BeforeEach
   public void inicializaEntidades() throws SQLException{
      ClienteDTO clienteDTO = new ClienteDTO("Astolfo");
      cliente = clService.inserirCliente(clienteDTO);
      System.out.println(cliente);

      crypto = new Crypto("ETH", "Etherium", 1000.0, 999.9);
      crService.saveCrypto(crypto);
      System.out.println(crypto);
   }
   
   
   @Test
   public void clienteDeveComprarCrypto(){
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
      ccService.insereCryptoCliente(new CryptoClienteDTO(1L, "ETH", new BigDecimal(10)));
      given()
         .contentType(ContentType.JSON)
         .body("{\"idCliente\": 1,\"codigoCrypto\": \"ETH\",\"quantidade\": \"1\"}")
         .when()
         .post("/cliente/venda")
         .then().statusCode(202)
         .assertThat().body(containsString("\"quantidade\":9.0"));
   }
}
