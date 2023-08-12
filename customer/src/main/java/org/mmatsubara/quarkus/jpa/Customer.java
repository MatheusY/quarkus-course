package org.mmatsubara.quarkus.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.time.Instant;

@Entity
@Table(name = "t_customers")
public class Customer {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "first_name", length = 50, nullable = false)
  private String firstName;
  @Column(name = "last_name", length = 50, nullable = false)
  private String lastName;
  @Column(name = "e_mail", length = 50, nullable = false)
  private String email;
  @Column(name = "created_date", nullable = false)
  private Instant createdDate = Instant.now();

  public Customer() {
  }

  public Customer(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }
}
