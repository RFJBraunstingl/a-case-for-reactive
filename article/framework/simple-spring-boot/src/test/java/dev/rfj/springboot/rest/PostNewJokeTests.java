package dev.rfj.springboot.rest;

import dev.rfj.springboot.data.JokeEntity;
import dev.rfj.springboot.data.JokeRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class PostNewJokeTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JokeRepository jokeRepository;

    @Test
    void postingNewJoke_createsItInRepository() throws Exception {
        String randomJoke = RandomStringUtils.randomAlphabetic(200);

        mockMvc.perform(post("/")
                .content(randomJoke)
                .contentType(MediaType.TEXT_PLAIN))
                .andReturn();

        Mockito.verify(jokeRepository, Mockito.times(1))
                .save(JokeEntity.forJoke(randomJoke));
    }
}
