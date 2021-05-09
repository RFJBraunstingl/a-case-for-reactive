package dev.rfj;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/joke")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class JokeResource {

    @Inject
    JokeRepository jokeRepository;

    @GET
    public String random() {
        return jokeRepository.randomJoke();
    }

    @POST
    public Response createJoke(String joke) {
        jokeRepository.save(joke);
        return Response.status(201).build();
    }
}