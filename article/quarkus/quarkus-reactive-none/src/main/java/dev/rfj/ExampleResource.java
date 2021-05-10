package dev.rfj;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/api/joke")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class ExampleResource {

    private final List<String> jokes = new ArrayList<>();

    {
        jokes.add("first one");
    }

    @GET
    public String hello() {
        return jokes.get(jokes.size() - 1);
    }

    @POST
    public String create(String joke) {
        jokes.add(joke);
        return joke;
    }
}