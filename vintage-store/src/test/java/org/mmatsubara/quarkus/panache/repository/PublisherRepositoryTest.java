package org.mmatsubara.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mmatsubara.quarkus.panache.model.Publisher;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;

import static io.smallrye.common.constraint.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PublisherRepositoryTest {

  @Test
  @TestTransaction
  public void shouldCreateAndFindAPublisher() {
    var count = Publisher.count();
    var listAll = Publisher.listAll().size();
    assertEquals(count, listAll);

    var publisher = new Publisher("name");

    Publisher.persist(publisher);
    assertEquals(count + 1, Publisher.count());

    assertNotNull(publisher.id);
    publisher = Publisher.findById(publisher.id);
    publisher = Publisher.findByName(publisher.name).orElseThrow(EntityNotFoundException::new);
    assertEquals("name", publisher.name);
    assertTrue(Publisher.findContainedName(publisher.name).size() >= 1);

    Publisher.deleteById(publisher.id);

    assertEquals(count, Publisher.count());
  }
}
