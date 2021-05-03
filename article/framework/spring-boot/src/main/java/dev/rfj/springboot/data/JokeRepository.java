package dev.rfj.springboot.data;

import org.springframework.data.repository.CrudRepository;

public interface JokeRepository extends CrudRepository<JokeEntity, Long> {}
