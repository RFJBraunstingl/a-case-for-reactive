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
    void creatingJoke_increasesCount() throws InterruptedException {
        long oldCount = jokeRepository.getCount();

        jokeRepository.save(randomAlphabetic(500)).await().indefinitely();

        assertEquals(oldCount + 1, jokeRepository.getCount());
    }

    @Test
    void deleteAllWorks() {
        jokeRepository.deleteAll();
        assertEquals(0, jokeRepository.getCount());
    }

    @Test
    void countIsCalculatedCorrectly() throws InterruptedException {
        jokeRepository.deleteAll();
        Thread.sleep(100);

        jokeRepository.save(randomAlphabetic(200)).await().indefinitely();
        assertEquals(1, jokeRepository.getCount());

        jokeRepository.save(randomAlphabetic(200)).await().indefinitely();
        assertEquals(2, jokeRepository.getCount());

        jokeRepository.save(randomAlphabetic(200)).await().indefinitely();
        assertEquals(3, jokeRepository.getCount());
    }

    @Test
    void onlyOneJokeInRepository_isReturnedWhenAskedForRandom() {
        jokeRepository.deleteAll();

        String randomJoke = randomAlphabetic(200);
        jokeRepository.save(randomJoke).await().indefinitely();

        assertEquals(randomJoke, jokeRepository.randomJoke().await().indefinitely());
    }

    @Test
    void randomJokeIsReturned() throws InterruptedException {
        String randomJoke1 = randomAlphabetic(200);
        String randomJoke2 = randomAlphabetic(200);

        jokeRepository.save(randomJoke1).await().indefinitely();
        jokeRepository.save(randomJoke2).await().indefinitely();

        long returnCountJoke1 = 0;
        long returnCountJoke2 = 0;

        int numberOfRequests = 100;
        for (int i = 0; i < numberOfRequests; i++) {
            String returnedJoke = jokeRepository.randomJoke().await().indefinitely();

            if (returnedJoke.equals(randomJoke1)) {
                returnCountJoke1++;
            } else if (returnedJoke.equals(randomJoke2)) {
                returnCountJoke2++;
            } else {
                fail("unexpected joke was returned! " + returnedJoke);
            }
        }

        int toleranceDelta = 5;
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
