package dev.rfj.reactivespringboot.joke;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class JokeRouter {

    @Bean
    public RouterFunction<ServerResponse> route(JokeHandler handler) {
        return RouterFunctions.route()
                .GET("/api/joke", accept(TEXT_PLAIN), handler::randomJoke)
                .POST("/api/joke", accept(TEXT_PLAIN), handler::createJoke)
                .build();
    }
}
