package dev.rfj;

import io.quarkus.redis.client.RedisClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@ApplicationScoped
public class RedisJokeRepository implements JokeRepository {

    private static final String SET_KEY_JOKES = "JOKES";

    @Inject
    RedisClient redisClient;

    @Override
    public String randomJoke() {
        // get a random member of the set storing jokes
        return redisClient.srandmember(singletonList(SET_KEY_JOKES)).toString();
    }

    @Override
    public void save(String newJoke) {
        // add the joke to the set specified by key
        // creates the set if it does not exist
        redisClient.sadd(asList(SET_KEY_JOKES, newJoke));
    }

    @Override
    public long getCount() {
        return redisClient.scard(SET_KEY_JOKES).toLong();
    }

    @Override
    public void deleteAll() {
        // unset the key storing the set
        redisClient.del(singletonList(SET_KEY_JOKES));
    }
}
