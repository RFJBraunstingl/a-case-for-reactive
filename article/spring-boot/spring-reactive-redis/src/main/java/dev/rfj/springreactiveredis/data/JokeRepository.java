package dev.rfj.springreactiveredis.data;

import reactor.core.publisher.Mono;

public interface JokeRepository {

    Mono<Long> deleteAll();

    Mono<Long> save(String joke);

    Mono<Long> count();

    Mono<String> random();
}
