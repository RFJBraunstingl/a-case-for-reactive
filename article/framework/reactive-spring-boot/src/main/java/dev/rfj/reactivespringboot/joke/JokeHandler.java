package dev.rfj.reactivespringboot.joke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Component
public class JokeHandler {

    private final JokeRepository repository;

    @Autowired
    public JokeHandler(JokeRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> randomJoke(ServerRequest req) {
        return repository.randomJoke().flatMap(joke -> ok().contentType(TEXT_PLAIN).bodyValue(joke));
    }

    public Mono<ServerResponse> createJoke(ServerRequest req) {
        return req.bodyToMono(String.class)
                .flatMap(repository::newJoke)
                .then(status(201).build());
    }
}
