package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
@Table(name = "t_publishers")
public class Publisher extends PanacheEntity {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(length = 30)
  public String name;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Publisher() {
  }

  public Publisher(String name) {
    this.name = name;
  }

  // ======================================
  // =              Methods               =
  // ======================================

  public static List<Publisher> findContainingName(String name) {
    return find("SELECT p FROM Publisher p WHERE p.name LIKE '%?1%'", name).list();
  }

  public static Optional<Publisher> findByName(String name) {
    return find("name", name).firstResultOptional();
  }
}
