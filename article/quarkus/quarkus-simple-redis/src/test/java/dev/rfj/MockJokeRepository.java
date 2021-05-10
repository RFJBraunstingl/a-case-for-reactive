package dev.rfj;

public class MockJokeRepository implements JokeRepository {

    public String randomJoke;
    public String createdJoke;
    public long count = 0;

    @Override
    public String randomJoke() {
        return randomJoke;
    }

    @Override
    public void save(String newJoke) {
        this.createdJoke = newJoke;
        count++;
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
