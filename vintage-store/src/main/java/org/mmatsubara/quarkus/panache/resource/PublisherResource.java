package org.mmatsubara.quarkus.panache.resource;

import org.mmatsubara.quarkus.panache.model.Publisher;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/api/publishers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class PublisherResource {

  @GET
  @Path("{id}")
  public Publisher findPublisherById(@PathParam("id") Long id) {
    return (Publisher) Publisher.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @GET
  public List<Publisher> findAllArtists() {
    return Publisher.listAll();
  }

  @Transactional(Transactional.TxType.REQUIRED)
  @POST
  public Response persistPublisher(Publisher publisher, @Context UriInfo uriInfo) {
    Publisher.persist(publisher);
    var uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(publisher.id));
    return Response.created(uriBuilder.build()).build();
  }

  @Transactional
  @DELETE
  @Path("{id}")
  public void deletePublisher(@PathParam("id") Long id) {
    Publisher.deleteById(id);
  }
}
