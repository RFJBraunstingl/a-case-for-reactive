package dev.rfj;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JokeResourceTests extends JokeResource {

    private final MockJokeRepository mockJokeRepository = new MockJokeRepository();

    public JokeResourceTests() {
        this.jokeRepository = mockJokeRepository;
    }

    @Test
    void gettingResource_returnsRandomJokeFromRepository() {
        mockJokeRepository.randomJoke = randomAlphabetic(200);

        assertEquals(
                getRandomJoke().await().indefinitely(),
                mockJokeRepository.randomJoke
        );
    }

    @Test
    void postingResource_createsNewJoke() {
        String randomJoke = randomAlphabetic(500);

        createNewJoke(randomJoke);

        assertEquals(
                randomJoke,
                mockJokeRepository.createdJoke
        );
    }
}
