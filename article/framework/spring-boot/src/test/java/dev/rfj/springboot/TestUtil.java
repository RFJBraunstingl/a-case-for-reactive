package dev.rfj.springboot;

import dev.rfj.springboot.data.JokeEntity;
import org.apache.commons.lang3.RandomStringUtils;

public class TestUtil {

    private TestUtil() {}

    public static JokeEntity randomJoke() {
        return JokeEntity.forJoke(RandomStringUtils.randomAlphabetic(200));
    }
}
