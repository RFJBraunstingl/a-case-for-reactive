package dev.rfj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JokeEntity {

    @Id
    @GeneratedValue
    public Long id;
    @Column(length = 501)
    public String joke;
}
