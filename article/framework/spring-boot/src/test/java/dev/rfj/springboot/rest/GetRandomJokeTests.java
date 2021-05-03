package dev.rfj.springboot.rest;

import dev.rfj.springboot.data.JokeEntity;
import dev.rfj.springboot.data.JokeRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetRandomJokeTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JokeRepository jokeRepository;

    @Test
    void givenAJoke_itIsReturned() throws Exception {
        jokeRepository.deleteAll();

        String expectedJoke = RandomStringUtils.randomAlphabetic(200);
        jokeRepository.save(JokeEntity.forJoke(expectedJoke));

        MvcResult result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertEquals(expectedJoke, content);
    }
}
