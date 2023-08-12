package org.mmatsubara.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mmatsubara.quarkus.jdbc.Artist;
import org.mmatsubara.quarkus.jpa.Customer;
import org.mmatsubara.quarkus.panache.model.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PurchaseOrderRepositoryTest {

  @Inject
  CustomerRepository customerRepository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindAPurchaseOrder() throws SQLException {
    var artist = new Artist("artist name", "artist bio");
    var publisher = new Publisher("publisher name");
    var book = new Book();
    book.title = "title of the book";
    book.price = new BigDecimal(10);
    book.isbn = "isbn";
    book.numberOfPages = 500;
    book.language = Language.PORTUGUESE;
    book.publisher = publisher;
    book.artist = artist;
    Book.persist(book);

    var customer = new Customer("customer first name", "customer last name", "customer email");
    customerRepository.persist(customer);

    var orderLine = new OrderLine();
    orderLine.item = book;
    orderLine.quantity = 2;

    var purchaseOrder = new PurchaseOrder();
    purchaseOrder.customer = customer;
    purchaseOrder.addOrderLine(orderLine);
    PurchaseOrder.persist(purchaseOrder);
  }
}
