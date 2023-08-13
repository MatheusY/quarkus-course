package org.mmatsubara.quarkus.panache.resource;

import org.mmatsubara.quarkus.jdbc.Artist;
import org.mmatsubara.quarkus.panache.repository.ArtistRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/api/artists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class ArtistResource {

  @Inject
  ArtistRepository repository;

  @GET
  @Path("{id}")
  public Artist findArtistById(@PathParam("id") Long id) {
    return repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @GET
  public List<Artist> findAllArtists() {
    return repository.listAllArtistsSorted();
  }

  @Transactional(Transactional.TxType.REQUIRED)
  @POST
  public Response persistArtist(Artist artist, @Context UriInfo uriInfo) {
    repository.persist(artist);
    var uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(artist.getId()));
    return Response.created(uriBuilder.build()).build();
  }

  @Transactional
  @DELETE
  @Path("{id}")
  public void deleteArtist(@PathParam("id") Long id) {
    repository.deleteById(id);
  }
}
