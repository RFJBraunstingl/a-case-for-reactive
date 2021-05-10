package dev.rfj;

import io.smallrye.mutiny.Uni;

public class MockJokeRepository implements JokeRepository {

    public String randomJoke;
    public String createdJoke;
    public long count = 0;

    @Override
    public Uni<String> randomJoke() {
        return Uni.createFrom().item(randomJoke);
    }

    @Override
    public Uni<String> save(String newJoke) {
        this.createdJoke = newJoke;
        count++;
        return Uni.createFrom().item(newJoke);
    }

    @Override
    public long getCount() {
        return count;
    }

    @Override
    public void deleteAll() {
        // noop
    }
}
