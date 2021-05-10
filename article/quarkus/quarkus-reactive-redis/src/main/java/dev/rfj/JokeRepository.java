package dev.rfj;

import io.smallrye.mutiny.Uni;

public interface JokeRepository {

    Uni<String> randomJoke();

    Uni<String> save(String newJoke);

    long getCount();

    void deleteAll();
}
