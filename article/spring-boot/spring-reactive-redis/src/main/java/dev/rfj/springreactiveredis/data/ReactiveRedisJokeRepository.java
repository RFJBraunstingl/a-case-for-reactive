package dev.rfj.springreactiveredis.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ReactiveRedisJokeRepository implements JokeRepository {

    private final static String JOKE_SET_KEY = "JOKES";

    private final ReactiveRedisOperations<String, String> redisOperations;

    @Autowired
    public ReactiveRedisJokeRepository(ReactiveRedisOperations<String, String> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public Mono<Long> deleteAll() {
        return redisOperations.delete(JOKE_SET_KEY);
    }

    @Override
    public Mono<Long> save(String joke) {
        return redisOperations.opsForSet().add(JOKE_SET_KEY, joke);
    }

    @Override
    public Mono<Long> count() {
        return redisOperations.opsForSet().size(JOKE_SET_KEY);
    }

    @Override
    public Mono<String> random() {
        return redisOperations.opsForSet().randomMember(JOKE_SET_KEY);
    }
}
