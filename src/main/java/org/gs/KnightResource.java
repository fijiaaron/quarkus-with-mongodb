package org.gs;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.types.ObjectId;
import org.gs.knight.Knight;
import org.gs.knight.KnightRepository;

@Path("/knights")
public class KnightResource
{
    @Inject 
    KnightRepository repository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get()
    {
        List<Knight> knights = repository.listAll();
        return Response.ok(knights).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) 
    {
        Knight knight = repository.findById(new ObjectId(id));
        return Response.ok(knight).build();
    }

    @GET
    @Path("/search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@PathParam("name") String name)
    {
        Response response;

        Knight knight = repository.findByName(name);
        if (knight == null) 
        {
            response =  Response.status(Status.NOT_FOUND).build();
        }
        else 
        {
            response = Response.ok(knight).build();
        }

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Knight knight) throws URISyntaxException
    {
        repository.persist(knight);
        return Response.created(new URI("/" +  knight.id)) .build();  
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Knight knight) 
    {
        knight.id = new ObjectId(id);
        repository.update(knight); 
        return Response.ok(knight).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id)
    {
        Knight knight = repository.findById(new ObjectId(id));  
        repository.delete(knight);  
        return Response.noContent().build();
    }
}