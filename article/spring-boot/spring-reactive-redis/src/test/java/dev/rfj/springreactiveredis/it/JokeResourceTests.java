package dev.rfj.springreactiveredis.it;

import dev.rfj.springreactiveredis.data.JokeRepository;
import dev.rfj.springreactiveredis.rest.JokeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = JokeController.class)
@Import(JokeRepository.class)
public class JokeResourceTests {

    @MockBean
    JokeRepository jokeRepository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void gettingResource_returnsJoke() {
        String randomJoke = "a random joke";
        Mono<String> monoOfRandomJoke = Mono.just(randomJoke);
        doReturn(monoOfRandomJoke).when(jokeRepository).random();

        webClient.get()
                .uri("/api/joke")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo(randomJoke);
    }

    @Test
    void postingResource_createsJoke() {
        String randomJoke = "a random joke";

        webClient.post()
                .uri("/api/joke")
                .bodyValue(randomJoke)
                .exchange()
                .expectStatus().is2xxSuccessful();

        verify(jokeRepository, times(1)).save(randomJoke);
    }
}
