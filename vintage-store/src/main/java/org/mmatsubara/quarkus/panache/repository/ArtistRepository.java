package org.mmatsubara.quarkus.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mmatsubara.quarkus.jdbc.Artist;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {


}
