package dev.rfj.reactivespringboot.joke;

import dev.rfj.reactivespringboot.joke.JokeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.TEXT_PLAIN;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ReactiveJokeControllerTests {

    @Autowired
    private WebTestClient client;

    @Autowired
    private JokeRepository jokeRepository;

    @Test
    void gettingResource_retrievesRandomJoke() {
        String randomJoke = "random joke";
        jokeRepository.deleteAll();
        jokeRepository.newJoke(randomJoke).block();

        client.get().uri("/api/joke")
                .accept(TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo(randomJoke);
    }

    @Test
    void postingToResource_createsJoke() {
        String randomJoke = "random joke";
        jokeRepository.deleteAll();

        client.post().uri("/api/joke")
                .body(BodyInserters.fromValue(randomJoke))
                .accept(TEXT_PLAIN)
                .exchange()
                .expectStatus().isEqualTo(201);

        assertEquals(1, jokeRepository.count().block());
    }
}
