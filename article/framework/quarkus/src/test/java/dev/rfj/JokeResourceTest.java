package dev.rfj;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
public class JokeResourceTest {

    @InjectMock
    JokeRepository jokeRepository;
    private String randomJoke;

    @BeforeEach
    void setUp() {
        randomJoke = RandomStringUtils.randomAlphabetic(500);
        Mockito.doReturn(randomJoke).when(jokeRepository).randomJoke();
    }

    @Test
    public void whenGettingJokeResource_randomJokeIsReturned() {
        given()
                .when().get("/joke")
                .then()
                .statusCode(200)
                .body(is(randomJoke));
    }

    @Test
    void whenPostingToResource_jokeIsStoredInRepository() {
        given()
                .body(randomJoke)
                .when().post("/joke")
                .then().statusCode(201);

        verifyOnce(jokeRepository).save(randomJoke);
    }

    private <T> T verifyOnce(T mock) {
        return verify(mock, times(1));
    }
}