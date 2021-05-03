package dev.rfj.springboot.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.apache.commons.lang3.RandomStringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SaveJokesTests {

    @Autowired
    JokeRepository jokeRepository;

    @BeforeEach
    void setUp() {
        jokeRepository.deleteAll();
    }

    @Test
    void jokesAreSaved() {
        String aRandomJoke = RandomStringUtils.randomAlphabetic(200);

        long id = jokeRepository.save(JokeEntity.forJoke(aRandomJoke)).getId();

        assertEquals(1, jokeRepository.count());

        JokeEntity storedEntity = jokeRepository.findById(id).get();
        assertEquals(aRandomJoke, storedEntity.getJoke());
    }
}
