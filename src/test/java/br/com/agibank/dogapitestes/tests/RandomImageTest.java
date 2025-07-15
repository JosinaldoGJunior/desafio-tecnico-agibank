package br.com.agibank.dogapitestes.tests;

import br.com.agibank.dogapitestes.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import javax.net.ssl.HttpsURLConnection;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RandomImageTest extends BaseTest {

    @Test
    @Tag("positivo")
    @DisplayName("Deve listar imagens randômicas")
    public void deveListarImagensRandomicas(){
        given()

        .when()
                .get("/breeds/image/random")
        .then()
                .statusCode(HttpsURLConnection.HTTP_OK)
                .body("status", equalTo("success"))
                .body("message", startsWith("https://images.dog.ceo/breeds/"))
        ;
    }

    @Test
    @Tag("positivo")
    @DisplayName("Deve listar uma quantidade específica de imagens randômicas")
    public void deveListarQuantidadeEspedificaDeImagens() {
        int quantidadePedida = 50;

        given()

                .when()
                .get("/breeds/image/random/{quantidade}", quantidadePedida)
                .then()
                .statusCode(HttpsURLConnection.HTTP_OK)
                .body("status", equalTo("success"))
                .body("message", hasSize(quantidadePedida));
    }

    @Test
    @Tag("comportamento")
    @DisplayName("Deve validar o comportamento ao enviar mais de 50 imagens")
    public void deveValidarEnvioSuperiorPermitido() {

        // TODO: API não retorna erro para requisições > 50. Em vez disso, limita o resultado a 50 imagens.
        int quantidadeSuperiorAoLimite = 51;

        given()
                .spec(requestSpec)
        .when()
                .get("/breeds/image/random/{quantidadeSuperiorAoLimite}",quantidadeSuperiorAoLimite)
        .then()
                .statusCode(HttpsURLConnection.HTTP_OK)
                .body("status", equalTo("success"))
                .body("message", hasSize(50));
    }


}