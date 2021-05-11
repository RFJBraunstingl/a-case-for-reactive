package dev.rfj.springreactiveredis.it;

import dev.rfj.springreactiveredis.data.JokeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JokeRepositoryTests {

    @Autowired
    JokeRepository jokeRepository;

    @Test
    void deleteAllWorks() {

        jokeRepository.save("a random joke").block();

        jokeRepository.deleteAll().block();

        assertEquals(0, jokeRepository.count().block());
    }

    @Test
    void savingJoke_increasesCount() {

        long oldCount = jokeRepository.count().block();

        jokeRepository.save("a new joke").block();

        assertEquals(oldCount + 1, jokeRepository.count().block());
    }

    @Test
    void random_returnsJoke() {

        jokeRepository.deleteAll().block();

        jokeRepository.save("a random joke").block();

        assertEquals("a random joke", jokeRepository.random().block());
    }
}
