package org.mmatsubara.quarkus.jpa;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerRepositoryTest {

  @Inject
  CustomerRepository customerRepository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindACustomer() {
    var customer = new Customer("first name", "last name", "email");

    customerRepository.persist(customer);

    assertNotNull(customer.getId());
    customer = customerRepository.findById(customer.getId());
    assertEquals("first name", customer.getFirstName());
  }
}
