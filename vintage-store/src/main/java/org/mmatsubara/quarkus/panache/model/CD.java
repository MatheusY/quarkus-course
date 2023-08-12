package org.mmatsubara.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CD extends Item {

  @Column(name = "music_company")
  public String musicCompany;
  @Column(length = 100)
  public String genre;
  @OneToMany(mappedBy = "cd")
  public List<Track> tracks = new ArrayList<>();
}
