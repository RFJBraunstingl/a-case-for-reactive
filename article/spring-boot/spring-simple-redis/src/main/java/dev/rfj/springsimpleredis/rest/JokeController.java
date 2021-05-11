package dev.rfj.springsimpleredis.rest;

import dev.rfj.springsimpleredis.data.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/joke")
public class JokeController {

    private final JokeRepository jokeRepository;

    @Autowired
    public JokeController(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @GetMapping(produces = "text/plain")
    public String getRandomJoke() {
        return jokeRepository.random();
    }

    @PostMapping(consumes = "text/plain")
    @ResponseStatus(CREATED)
    public void createNewJoke(@RequestBody String joke) {
        jokeRepository.save(joke);
    }
}
