package dev.rfj;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class JokeRepositoryTests {

    @Inject
    JokeRepository jokeRepository;
    private String randomJoke;

    @BeforeEach
    void setUp() {
        jokeRepository.deleteAll();
        randomJoke = RandomStringUtils.randomAlphabetic(500);
    }

    @Test
    void jokesAreStored() {
        jokeRepository.save(randomJoke);
        Assertions.assertEquals(randomJoke, jokeRepository.randomJoke());
    }
}
