package org.mmatsubara.quarkus.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.SQLException;

@ApplicationScoped
public class CustomerRepository {

  @Inject
  EntityManager em;

  public void persist(Customer customer) {
    em.persist(customer);
  }

  public Customer findById(Long id) {
    return em.find(Customer.class, id);
  }


}
