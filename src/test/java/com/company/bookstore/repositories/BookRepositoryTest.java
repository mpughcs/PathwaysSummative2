package com.company.bookstore.repositories;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
    private Book b = new Book("100000000000", LocalDate.now(), author1, "lion book", publisher1, new BigDecimal("12.99"));

    @BeforeEach
    void setUp() {

        author1= authorRepository.save(author1);
        publisher1 = pubRepository.save(publisher1);
        b=repo.save(b);

    }
    @AfterEach
    void cleanUp(){
        repo.deleteAll();
        authorRepository.deleteAll();
        pubRepository.deleteAll();
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

        Optional<Book> retrievedBook;


        repo.delete(b);

        retrievedBook = repo.findById(b.getId());

        assertFalse(retrievedBook.isPresent(), "Book should be deleted");

    }


    @Test
    void shouldSearchBookByAuthorId(){
        List<Book> expected = new ArrayList<>();
        expected.add(b);
        List<Book> recieved = new ArrayList<>();
        for(Book book : repo.findAll()){
            if (book.getAuthorId().getAuthor_id().intValue() == b.getAuthorId().getAuthor_id()){
                recieved.add(book);
            }
        }
        assertEquals(expected,recieved);
    }




}