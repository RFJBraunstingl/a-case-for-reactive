package dev.rfj.reactivespringboot.joke;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface JokeRepository extends ReactiveCrudRepository<JokeEntity, Long> {

    default Mono<JokeEntity> newJoke(String joke) {
        return save(JokeEntity.from(joke));
    }

    default Mono<String> randomJoke() {
        // return the last one added
        return count().transform(this::findById)
                .map(JokeEntity::getJoke);
    }
}
