package dev.rfj.springsimpleredis.data;

public interface JokeRepository {
    void deleteAll();

    long count();

    void save(String joke);

    String random();
}
