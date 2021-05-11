package dev.rfj.springboot.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JokeEntity {

    @Id
    @GeneratedValue
    private long id;
    private String joke;

    public static JokeEntity empty() {
        return forJoke("");
    }

    public static JokeEntity forJoke(String joke) {
        return new JokeEntity(0, joke);
    }
}
