package org.mmatsubara.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mmatsubara.quarkus.jdbc.Artist;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

  @Inject
  ArtistRepository artistRepository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindAnArtist() {
    var count = artistRepository.count();
    var listAll = artistRepository.listAll().size();
    assertEquals(count, listAll);
    assertEquals(artistRepository.listAllArtistsSorted().size(), listAll);

    Artist artist = new Artist("name", "bio");

    artistRepository.persist(artist);
    assertEquals(count + 1, artistRepository.count());

    assertNotNull(artist.getId());
    artist = artistRepository.findById(artist.getId());
    assertEquals("name", artist.getName());

    artistRepository.deleteById(artist.getId());
    assertEquals(count, artistRepository.count());
  }
}
