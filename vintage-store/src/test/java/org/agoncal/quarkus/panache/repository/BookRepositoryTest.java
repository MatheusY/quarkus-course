package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.Language;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class BookRepositoryTest {

  @Inject
  ArtistRepository artistRepository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindABook() {
    long nbArtists = artistRepository.count();
    long nbPublishers = Publisher.count();
    long nbBooks = Book.count();

    // Creates an Artist
    Artist artist = new Artist();
    artist.setName("name");
    artist.setBio("bio");
    // Creates a Publisher
    Publisher publisher = new Publisher();
    publisher.name = "name";
    // Creates a Book
    Book book = new Book();
    book.title = "title";
    book.description = "description";
    book.price = new BigDecimal(10);
    book.isbn = "ISBN";
    book.nbOfPages = 500;
    book.publicationDate = LocalDate.now().minusDays(100);
    book.language = Language.ENGLISH;
    // Sets the Publisher and Artist to the Book
    book.publisher = publisher;
    book.artist = artist;

    // Persists the Book with one Publisher and one Artist
    Book.persist(book);
    assertNotNull(book.id);
    assertNotNull(book.publisher.id);
    assertNotNull(book.artist.getId());

    assertEquals(nbArtists + 1, artistRepository.count());
    assertEquals(nbPublishers + 1, Publisher.count());
    assertEquals(nbBooks + 1, Book.count());

    // Gets the Book
    book = Book.findById(book.id);
    assertEquals("title", book.title);

    // Deletes the Book
    Book.deleteById(book.id);
    assertEquals(nbArtists + 1, artistRepository.count());
    assertEquals(nbPublishers + 1, Publisher.count());
    assertEquals(nbBooks, Book.count());
  }
}
