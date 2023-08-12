package org.mmatsubara.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mmatsubara.quarkus.jdbc.Artist;

import javax.inject.Inject;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerRepositoryTest {

  @Inject
  ArtistRepository artistRepository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindAnArtist() throws SQLException {
    Artist artist = new Artist("name", "bio");

    artistRepository.persist(artist);

    assertNotNull(artist.getId());
    artist = artistRepository.findById(artist.getId());
    assertEquals("name", artist.getName());
  }
}
