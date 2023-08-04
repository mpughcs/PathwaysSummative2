package com.company.bookstore.repositories;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository pubRepository;

    @Autowired
    private BookRepository repo;

    private Author author1 = new Author("m", "pugh", "main", "longbeach", "CA", "90803", "314-299-3259", "mp@gmail.com");
    private Publisher publisher1 = new Publisher("z", "south", "LA", "CA", "90909", "111-111-1111", "z@hotmail.com");
    Book b = new Book("100000000000", LocalDate.now(), author1, "lion book", publisher1, new BigDecimal("12.99"));

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        authorRepository.deleteAll();
        pubRepository.deleteAll();

        author1= authorRepository.save(author1);
        publisher1 = pubRepository.save(publisher1);
        b=repo.save(b);
        repo.save(b);

        System.out.println(b.getId());

    }

//    Create
    @Test
    void shouldAddBook() {
        Optional<Book> expected = repo.findById(b.getId());
        assertEquals(expected.get(),b);
    }
//    Read by ID
    @Test
    void shouldFindBookById(){
        Optional<Book> expected = repo.findById(b.getId());
        assertEquals(expected.get(),b);
    }

//    Read all
    @Test
    void shouldFindBooks(){
        List<Book> expected = new ArrayList<>();
        expected.add(b);
        assertEquals(repo.findAll(),expected);
    }

//    Update

    @Test
    void shouldUpdateBook(){
        Book updated= new Book("100000000000", LocalDate.now(), author1, "tiger book", publisher1, new BigDecimal("12.99"));
        updated.setId(b.getId());
        repo.save(updated);
        Optional<Book> retrievedBook = repo.findById(b.getId());
        assertEquals(updated, retrievedBook.get());
    }

//    delete
    @Test
    void shouldDeleteBook(){

//        make sure customer exists
        Optional<Book> retrievedBook = repo.findById(b.getId());

        // Perform the assertion to check if the customer exists before deletion

        // Delete the customer from the database
        repo.delete(b);

        // Try to retrieve the customer after deletion
        retrievedBook = repo.findById(b.getId());

        // Perform the assertion to check if the customer is deleted
        assertFalse(retrievedBook.isPresent(), "Customer should be deleted");

    }


    @Test
    void shouldSearchBookByAuthorId(){
        List<Book> expected = new ArrayList<>();
        expected.add(b);
        // Create an Author object with the same ID as the author of the book 'b'
        Author author = new Author();
        author.setAuthor_id(b.getAuthorId().getAuthor_id());
        assertEquals(repo.findByAuthorId(author.getAuthor), expected);
    }

}