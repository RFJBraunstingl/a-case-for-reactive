package dev.rfj.springreactiveredis.it;

import dev.rfj.springreactiveredis.data.JokeRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JokeRepositoryTests {

    @Autowired
    JokeRepository jokeRepository;
    private String randomJoke;

    @BeforeEach
    void setUp() {
        randomJoke = RandomStringUtils.randomAlphabetic(200);
    }

    @Test
    void deleteAllWorks() {

        jokeRepository.save(randomJoke).block();

        jokeRepository.deleteAll().block();

        assertEquals(0, jokeRepository.count().block());
    }

    @Test
    void savingJoke_increasesCount() {

        long oldCount = jokeRepository.count().block();

        jokeRepository.save(randomJoke).block();

        assertEquals(oldCount + 1, jokeRepository.count().block());
    }

    @Test
    void random_returnsJoke() {

        jokeRepository.deleteAll().block();

        jokeRepository.save(randomJoke).block();

        assertEquals(randomJoke, jokeRepository.random().block());
    }
}
