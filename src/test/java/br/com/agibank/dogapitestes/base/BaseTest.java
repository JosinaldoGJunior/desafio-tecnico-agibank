package br.com.agibank.dogapitestes.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static RequestSpecification requestSpec;

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "https://dog.ceo/api";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        requestSpec = builder.build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
}
