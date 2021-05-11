package dev.rfj.springsimpleredis.it;

import dev.rfj.springsimpleredis.data.JokeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JokeRepositoryTests {

    private String randomJoke() {
        return randomAlphabetic(500);
    }

    @Autowired
    JokeRepository jokeRepository;

    @Test
    void deleteAllWorks() {

        jokeRepository.save(randomJoke());

        jokeRepository.deleteAll();

        assertEquals(0, jokeRepository.count());
    }

    @Test
    void savingJokes_increasesCount() {
        long oldCount = jokeRepository.count();

        jokeRepository.save(randomJoke());

        assertEquals(oldCount + 1, jokeRepository.count());
    }

    @Test
    void randomJokeReturnsJoke() {
        jokeRepository.deleteAll();

        String joke = randomJoke();
        jokeRepository.save(joke);

        assertEquals(joke, jokeRepository.random());
    }
}
