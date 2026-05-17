package com.uespi.gabriel;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void deveRenderizarPaginaDeSincronizacao() {
        given()
          .when().get("/sincronizacao")
          .then()
             .statusCode(200)
             .body(containsString("Sincronizacao de Relogios"));
    }

    @Test
    void deveCriarServidor() {
        given()
          .contentType("application/json")
          .body("{\"horaServidor\":\"10:00:00\"}")
          .when().post("/sd/criarServidor")
          .then()
             .statusCode(200)
             .body(containsString("Servidor criado com hora 10:00"));
    }

    @Test
    void deveListarClientesNaOrdemDaHoraTratada() {
        given()
          .contentType("application/json")
          .body("{\"horaServidor\":\"10:00:00\"}")
          .when().post("/sd/criarServidor")
          .then()
             .statusCode(200);

        given()
          .contentType("application/json")
          .body("{\"nomeCliente\":\"Cliente 1\",\"horaLocal\":\"09:00:00\",\"horaEnvio\":\"12:00:00\"}")
          .when().post("/sd/sincronizarCliente")
          .then()
             .statusCode(200);

        given()
          .contentType("application/json")
          .body("{\"nomeCliente\":\"Cliente 2\",\"horaLocal\":\"11:00:00\",\"horaEnvio\":\"08:00:00\"}")
          .when().post("/sd/sincronizarCliente")
          .then()
             .statusCode(200);

        given()
          .when().get("/sd/clientes")
          .then()
             .statusCode(200)
             .body("[0].nomeCliente", is("Cliente 2"))
             .body("[0].horaEnvioTratada", is("07:00:00"));
    }

    @Test
    void deveLimparEstados() {
        given()
          .contentType("application/json")
          .body("{\"horaServidor\":\"10:00:00\"}")
          .when().post("/sd/criarServidor")
          .then()
             .statusCode(200);

        given()
          .when().get("/sd/limparEstados")
          .then()
             .statusCode(200);

        given()
          .when().get("/sd/servidor")
          .then()
             .statusCode(404);

        given()
          .when().get("/sd/clientes")
          .then()
             .statusCode(200)
             .body("size()", is(0));
    }

}
