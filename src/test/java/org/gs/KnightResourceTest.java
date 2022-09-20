package org.gs;

import java.util.List;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.gs.knight.Knight;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class KnightResourceTest {

    
    @Test
    public void testKnightsEndpoint() {



        Response response = given()
        .when().get("/knights")
        .then().statusCode(200)
        .and().contentType(ContentType.JSON)
        .extract().response();

        String json = response.getBody().asPrettyString();
        System.out.println("json: " + json);

        List<Knight> knights = response.as(Knight.listType.getType());
       
        assertThat(knights.size()).isEqualTo(2);

        knights.forEach(knight-> {
            System.out.println(knight);
            System.out.println(knight.name);
        });

    }



}