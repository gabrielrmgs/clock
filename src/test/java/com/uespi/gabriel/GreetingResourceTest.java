package com.uespi.gabriel;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

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

}
