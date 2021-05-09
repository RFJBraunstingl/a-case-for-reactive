package dev.rfj.springbootnopersistence.joke;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/joke")
public class JokeController {

    private final List<String> jokes = new ArrayList<>();

    @GetMapping
    public String getRandomJoke() {
        if (jokes.size() == 0)
            return "";

        return jokes.get(jokes.size() - 1);
    }

    @PostMapping
    public void createNewJoke(@RequestBody String jokeString) {
        jokes.add(jokeString);
    }
}
