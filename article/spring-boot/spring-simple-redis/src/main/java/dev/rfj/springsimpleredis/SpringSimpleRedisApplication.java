package dev.rfj.springsimpleredis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@SpringBootApplication
public class SpringSimpleRedisApplication {

    @Value("${spring.redis.host}")
    private String redisHost;

    public static void main(String[] args) {
        SpringApplication.run(SpringSimpleRedisApplication.class, args);
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, 6379));
    }
}
