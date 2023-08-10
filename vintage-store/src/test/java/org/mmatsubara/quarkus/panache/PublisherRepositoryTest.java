package org.mmatsubara.quarkus.panache;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mmatsubara.quarkus.jdbc.Artist;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PublisherRepositoryTest {

  @Test
  @TestTransaction
  public void shouldCreateAndFindAPublisher() throws SQLException {
    var publisher = new Publisher("name");

    Publisher.persist(publisher);

    assertNotNull(publisher.id);
    publisher = Publisher.findById(publisher.id);
    assertEquals("name", publisher.name);
  }
}
