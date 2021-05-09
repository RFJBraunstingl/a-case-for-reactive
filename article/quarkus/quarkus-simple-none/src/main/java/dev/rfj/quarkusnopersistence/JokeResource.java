package dev.rfj.quarkusnopersistence;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/api/joke")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
@ApplicationScoped
public class JokeResource {

    private final List<String> jokes = new ArrayList<>();

    @GET
    public String random() {
        if (jokes.size() == 0)
            return "";

        return jokes.get(jokes.size() - 1);
    }

    @POST
    public Response createJoke(String joke) {
        jokes.add(joke);
        return Response.status(201).build();
    }
}
