package br.com.agibank.dogapitestes.tests;

import br.com.agibank.dogapitestes.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import javax.net.ssl.HttpsURLConnection;
import static io.restassured.RestAssured.given;

public class BreedsTest extends BaseTest {

    @Test
    @Tag("positivo")
    @DisplayName("Deve Listar todas as raças com sucesso")
    public void deveListarTodasAsRacas(){
        given()
                .spec(requestSpec)
        .when()
                .get("/breeds/list/all")
        .then()
                .statusCode(HttpsURLConnection.HTTP_OK)
                .body("message",not(empty()))
                .body("status",equalTo("success"))
        ;
    }

    @Test
    @Tag("positivo")
    @DisplayName("Deve listar imagens de raça existente")
    public void deveListarImagensDeRacaExistente(){

        given()
                .spec(requestSpec)
                .pathParam("breed","hound")
        .when()
                .get("/breed/{breed}/images")
        .then()
                .statusCode(HttpsURLConnection.HTTP_OK)
                .body("status",equalTo("success"))
                .body("message",not(empty()))
        ;
    }

    @Test
    @Tag("negativo")
    @DisplayName("não deve listar imagens com raça inexistente")
    public void NaoDeveListarImagensDeRacaInexistente(){

        given()
                .spec(requestSpec)
                .pathParam("breed","raca-inexistente")
        .when()
                .get("/breed/{breed}/images")
        .then()
                .statusCode(HttpsURLConnection.HTTP_NOT_FOUND)
                .body("status",equalTo("error"))
                .body("message",equalTo("Breed not found (master breed does not exist)"))
        ;
    }

}