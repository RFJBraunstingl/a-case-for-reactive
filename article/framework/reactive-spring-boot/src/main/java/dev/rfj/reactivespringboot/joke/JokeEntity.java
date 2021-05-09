package dev.rfj.reactivespringboot.joke;

import org.springframework.data.annotation.Id;

public class JokeEntity {

    @Id
    private long id;
    private String joke;

    public JokeEntity() {
    }

    public JokeEntity(long id, String joke) {
        this.id = id;
        this.joke = joke;
    }

    public static JokeEntity from(String joke) {
        return new JokeEntity(0, joke);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return "JokeEntity{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                '}';
    }
}
