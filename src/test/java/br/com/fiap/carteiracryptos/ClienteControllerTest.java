package br.com.fiap.carteiracryptos;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ClienteControllerTest {

   @Test
   public void deveCriarCliente() {
      given()
         .contentType(ContentType.JSON)
         .body("{\"nome\": \"Alexandre\"}")
         .when()
         .post("/cliente")
         .then().statusCode(201);
   }

   @Test
   public void deveListarClienteInserido() {
      given()
         .contentType(ContentType.JSON)
         .body("{\"nome\": \"Alexandre\"}")
         .post("/cliente");
      when()
         .get("/cliente/lista")
         .then().statusCode(200).assertThat().body("size()", is(1))
         .body(containsString("\"nome\":\"Alexandre\""));
   }

   @Test
   public void deveConsultarClienteInserido() {
      given()
         .contentType(ContentType.JSON)
         .body("{\"nome\": \"Alexandre\"}")
         .post("/cliente");
      when()
         .get("/cliente/1")
         .then().statusCode(302)
         .assertThat().body(containsString("\"nome\":\"Alexandre\""));
   }
}
