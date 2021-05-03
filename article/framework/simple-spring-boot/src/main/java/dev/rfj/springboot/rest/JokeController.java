package dev.rfj.springboot.rest;

import dev.rfj.springboot.data.JokeEntity;
import dev.rfj.springboot.data.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/")
public class JokeController {

    private final JokeRepository jokeRepository;

    @Autowired
    public JokeController(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @GetMapping
    public String getRandomJoke() {
        long randomId = ThreadLocalRandom.current().nextLong(jokeRepository.count()) + 1;
        JokeEntity entity = jokeRepository.findById(randomId).orElse(JokeEntity.empty());
        return entity.getJoke();
    }

    @PostMapping
    public void createNewJoke(@RequestBody String jokeString) {
        jokeRepository.save(JokeEntity.forJoke(jokeString));
    }
}
