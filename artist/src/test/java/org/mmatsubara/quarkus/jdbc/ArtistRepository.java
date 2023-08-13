package org.mmatsubara.quarkus.jdbc;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

@ApplicationScoped
public class ArtistRepository {

  @Inject
  DataSource dataSource;

  public void persist(Artist artist) throws SQLException {
    var conn = dataSource.getConnection();
    var sql = "INSERT INTO t_artists (id, name, bio, created_date) VALUES (?, ?, ?, ?)";
    artist.setId(Math.abs(new Random().nextLong()));

    try (var ps = conn.prepareStatement(sql)) {
      ps.setLong(1, artist.getId());
      ps.setString(2, artist.getName());
      ps.setString(3, artist.getBio());
      ps.setTimestamp(4, Timestamp.from(artist.getCreatedDate()));
      ps.executeUpdate();
    }
    conn.close();
  }

  public Artist findById(Long id) throws SQLException {
    var conn = dataSource.getConnection();
    var sql = "SELECT id, name, bio, created_date FROM t_artists WHERE id = ?";
    var artist = new Artist();
    try (var ps = conn.prepareStatement(sql)) {
      ps.setLong(1, id);

      var rs = ps.executeQuery();
      while (rs.next()) {
        artist.setId(rs.getLong(1));
        artist.setName(rs.getString(2));
        artist.setBio(rs.getString(3));
        artist.setCreatedDate(rs.getTimestamp(4).toInstant());
      }
    }
    conn.close();
    return artist;
  }

}
