package dev.rfj.springreactiveredis.rest;

import dev.rfj.springreactiveredis.data.JokeRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/joke")
public class JokeController {

    private final JokeRepository jokeRepository;

    public JokeController(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @GetMapping
    public Mono<String> getRandomJoke() {
        return jokeRepository.random();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<Long> createNewJoke(@RequestBody String joke) {
        return jokeRepository.save(joke);
    }
}
