package com.example.qaapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskApiRestAssuredTest {

    @LocalServerPort
    int port;

    @BeforeAll
    static void setupRestAssured() {
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void getTasksDeveRetornarArrayVazio() {
        given()
                .port(port)
                .when()
                .get("/api/tasks")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", hasSize(0));
    }

    @Test
    void postTasksDeveCriarEstreamRetornarObjeto() {
        var payload = """
      {
        "title":"Testar com RestAssured",
        "done":false
      }
    """;

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/api/tasks")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("title", equalTo("Testar com RestAssured"))
                .body("done", equalTo(false));
    }
}
