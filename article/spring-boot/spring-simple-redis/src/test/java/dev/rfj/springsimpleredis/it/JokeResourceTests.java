package dev.rfj.springsimpleredis.it;

import dev.rfj.springsimpleredis.data.JokeRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JokeResourceTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JokeRepository jokeRepository;

    @Test
    void gettingJokeResource_returnsJoke() throws Exception {
        String randomJoke = RandomStringUtils.randomAlphabetic(200);
        doReturn(randomJoke).when(jokeRepository).random();

        mockMvc.perform(get("/api/joke"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(randomJoke));
    }

    @Test
    void postingJokeResource_createsJoke() throws Exception {
        String randomJoke = RandomStringUtils.randomAlphabetic(200);

        mockMvc.perform(
                post("/api/joke")
                        .contentType(TEXT_PLAIN)
                        .content(randomJoke)
        ).andExpect(status().is(201));

        verify(jokeRepository, times(1))
                .save(randomJoke);
    }
}
