package org.mmatsubara.quarkus.jdbc;

import java.time.Instant;

public class Artist {

  private Long id;
  private String name;
  private String bio;
  private Instant createDate = Instant.now();

  public Artist() {
  }

  public Artist(String name, String bio) {
    this.name = name;
    this.bio = bio;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }
}
