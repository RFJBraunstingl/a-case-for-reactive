package it;

import dev.rfj.JokeRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class JokeRepositoryTests {

    @Inject
    JokeRepository jokeRepository;

    @Test
    void creatingJoke_increasesCount() {
        long oldCount = jokeRepository.getCount();

        jokeRepository.save(randomAlphabetic(500));

        assertEquals(oldCount + 1, jokeRepository.getCount());
    }

    @Test
    void deleteAllWorks() {
        jokeRepository.deleteAll();
        assertEquals(0, jokeRepository.getCount());
    }

    @Test
    void countIsCalculatedCorrectly() {
        jokeRepository.deleteAll();

        jokeRepository.save(randomAlphabetic(200));
        assertEquals(1, jokeRepository.getCount());

        jokeRepository.save(randomAlphabetic(200));
        assertEquals(2, jokeRepository.getCount());

        jokeRepository.save(randomAlphabetic(200));
        assertEquals(3, jokeRepository.getCount());
    }

    @Test
    void onlyOneJokeInRepository_isReturnedWhenAskedForRandom() {
        jokeRepository.deleteAll();

        String randomJoke = randomAlphabetic(200);
        jokeRepository.save(randomJoke);

        assertEquals(randomJoke, jokeRepository.randomJoke());
    }

    @Test
    void randomJokeIsReturned() throws InterruptedException {
        String randomJoke1 = randomAlphabetic(200);
        String randomJoke2 = randomAlphabetic(200);

        jokeRepository.save(randomJoke1);
        jokeRepository.save(randomJoke2);

        long returnCountJoke1 = 0;
        long returnCountJoke2 = 0;

        int numberOfRequests = 100;
        for (int i = 0; i < numberOfRequests; i++) {
            String returnedJoke = jokeRepository.randomJoke();

            if (returnedJoke.equals(randomJoke1)) {
                returnCountJoke1++;
            } else if (returnedJoke.equals(randomJoke2)) {
                returnCountJoke2++;
            } else {
                fail("unexpected joke was returned! " + returnedJoke);
            }
        }

        int toleranceDelta = 15;
        int numberOfJokes = 2;
        int ideal = numberOfRequests / numberOfJokes;
        int expectedMin = ideal - toleranceDelta;
        int expectedMax = ideal + toleranceDelta;

        assertTrue(expectedMin < returnCountJoke1);
        assertTrue(expectedMax > returnCountJoke1);
        assertTrue(expectedMin < returnCountJoke2);
        assertTrue(expectedMax > returnCountJoke2);
    }
}
