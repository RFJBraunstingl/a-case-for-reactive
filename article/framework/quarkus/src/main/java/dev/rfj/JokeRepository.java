package dev.rfj;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
@Transactional
public class JokeRepository implements PanacheRepository<JokeEntity> {

    public String randomJoke() {
        long randomId = ThreadLocalRandom.current().nextLong(count()) + 1;
        JokeEntity randomEntity = findByIdOptional(randomId).orElseThrow();
        return randomEntity.joke;
    }

    public void save(String joke) {
        JokeEntity entity = new JokeEntity();
        entity.joke = joke;
        persist(entity);
    }
}