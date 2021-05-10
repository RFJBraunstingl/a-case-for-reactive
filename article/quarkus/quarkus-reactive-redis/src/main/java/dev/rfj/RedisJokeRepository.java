package dev.rfj;

import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@ApplicationScoped
public class RedisJokeRepository implements JokeRepository {

    private static final String SET_KEY_JOKES = "JOKES";

    @Inject
    ReactiveRedisClient redisClient;

    @Override
    public Uni<String> randomJoke() {
        // get a random member of the set storing jokes
        return redisClient.srandmember(singletonList(SET_KEY_JOKES)).map(Response::toString);
    }

    @Override
    public Uni<String> save(String newJoke) {
        // add the joke to the set specified by key
        // creates the set if it does not exist
        return redisClient.sadd(asList(SET_KEY_JOKES, newJoke)).map(Response::toString);
    }

    @Override
    public long getCount() {
        return redisClient.scard(SET_KEY_JOKES).await().indefinitely().toLong();
    }

    @Override
    public void deleteAll() {
        // unset the key storing the set
        redisClient.del(singletonList(SET_KEY_JOKES)).await().indefinitely().toLong();
    }
}
