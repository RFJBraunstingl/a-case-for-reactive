package it;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class JokeResourceBlackBoxTests {

    @Test
    void postingJokeResource_createsNewJoke() {
        given()
                .body(randomAlphabetic(500))
                .contentType(TEXT_PLAIN)
                .when().post("/api/joke")
                .then().statusCode(201);
    }

    @Test
    void gettingJokeResource_returnsJokeAnd200() {
        given()
                .when().get("/api/joke")
                .then()
                .statusCode(200)
                .body(is(not(empty())));
    }
}
