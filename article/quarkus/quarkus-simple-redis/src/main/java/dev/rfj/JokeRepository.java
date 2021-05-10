package dev.rfj;

public interface JokeRepository {

    String randomJoke();

    void save(String newJoke);

    long getCount();

    void deleteAll();
}
