package org.mmatsubara.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mmatsubara.quarkus.jdbc.Artist;
import org.mmatsubara.quarkus.jpa.Customer;

import javax.inject.Inject;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class CustomerRepositoryTest {

  @Inject
  CustomerRepository repository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindACustomer() {
    var customer = new Customer("first name", "last name", "email");

    repository.persist(customer);

    assertNotNull(customer.getId());
    customer = repository.findById(customer.getId());
    assertEquals("first name", customer.getFirstName());
    assertTrue(repository.listAllDans().size() <= repository.count());
  }
}
