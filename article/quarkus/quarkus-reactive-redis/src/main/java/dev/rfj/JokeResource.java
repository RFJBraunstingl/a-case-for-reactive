package dev.rfj;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/api/joke")
@Consumes(TEXT_PLAIN)
@Produces(TEXT_PLAIN)
public class JokeResource {

    @Inject
    JokeRepository jokeRepository;

    @GET
    public Uni<String> getRandomJoke() {
        return jokeRepository.randomJoke();
    }

    @POST
    public Response createNewJoke(String newJoke) {
        jokeRepository.save(newJoke);
        return Response.status(201).build();
    }
}
