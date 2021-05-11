package dev.rfj.springsimpleredis.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisJokeRepository implements JokeRepository {

    private static final String JOKE_SET_KEY = "JOKES";

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisJokeRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void deleteAll() {
        redisTemplate.unlink(JOKE_SET_KEY);
    }

    @Override
    public long count() {
        Long size = redisTemplate.opsForSet().size(JOKE_SET_KEY);
        return size == null ? 0 : size;
    }

    @Override
    public void save(String joke) {
        redisTemplate.opsForSet().add(JOKE_SET_KEY, joke);
    }

    @Override
    public String random() {
        return redisTemplate.opsForSet().randomMember(JOKE_SET_KEY);
    }
}
