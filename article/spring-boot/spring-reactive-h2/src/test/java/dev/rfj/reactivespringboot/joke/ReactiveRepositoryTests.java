package dev.rfj.reactivespringboot.joke;

import dev.rfj.reactivespringboot.joke.JokeEntity;
import dev.rfj.reactivespringboot.joke.JokeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ReactiveRepositoryTests {

    @Autowired
    private JokeRepository jokeRepository;

    @Test
    void jokesAreStored() {
        long oldCount = jokeRepository.count().blockOptional().orElseThrow();

        jokeRepository.newJoke("this is a joke").block();

        long newCount = jokeRepository.count().blockOptional().orElseThrow();
        assertEquals(oldCount + 1, newCount);
    }

    @Test
    void randomJoke_isReturned() {
        jokeRepository.deleteAll().block();

        jokeRepository.save(JokeEntity.from("test"))
                .then(jokeRepository.randomJoke())
                .subscribe(retrievedJoke -> assertEquals(retrievedJoke, "test"));
    }
}
