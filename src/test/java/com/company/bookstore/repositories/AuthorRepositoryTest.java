package com.company.bookstore.repositories;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorRepositoryTest {


    @Autowired
    AuthorRepository authorRepo;
    //Author author3 = new Author("rob", "TV", "Ton", "Austin", "TX", "78750", "5555555555", "timmy@gmail.com");



    @BeforeEach
    public void setUp() throws Exception {
        authorRepo.deleteAll();
        //author3 = authorRepo.save(author3);

    }

    @Test
    public void shouldAddAuthor() {
        //Set up
        Author author = new Author();
        author.setFirstName("bob");
        author.setLastName("miller");
        author.setState("TX");
        author.setCity("Austin");
        author.setStreet("Faro");
        author.setEmail("bob@gmail.com");
        author.setPostal_code("78741");
        author.setPhone("5127707160");

        //Save customer
        author = authorRepo.save(author);

        //Assert
        Optional<Author> author1 = authorRepo.findById(author.getAuthor_id());

        assertEquals(author1.get(), author);
    }
    @Test
    public void shouldGetAllAuthors() {
        //Set up
        Author author = new Author();
        author.setFirstName("bob");
        author.setLastName("miller");
        author.setState("TX");
        author.setCity("Austin");
        author.setStreet("Faro");
        author.setEmail("bob@gmail.com");
        author.setPostal_code("78741");
        author.setPhone("5127707160");

        //Save customer
        authorRepo.save(author);

        //Set up
        Author author2 = new Author();
        author2.setFirstName("steve");
        author2.setLastName("tum");
        author2.setState("AZ");
        author2.setCity("Houston");
        author2.setStreet("Faro");
        author2.setEmail("bob@gmail.com");
        author2.setPostal_code("78741");
        author2.setPhone("5555555555");

        //Save customer
        authorRepo.save(author2);

        List<Author> authorList = authorRepo.findAll();

        //Assert...
        assertEquals(2, authorList.size());
    }

    @Test
    public void shouldGetAuthorByID() {
        //Set up
        Author author = new Author();
        author.setFirstName("bob");
        author.setLastName("miller");
        author.setState("TX");
        author.setCity("Austin");
        author.setStreet("Faro");
        author.setEmail("bob@gmail.com");
        author.setPostal_code("78741");
        author.setPhone("5127707160");

        //Save customer
        authorRepo.save(author);

        Optional<Author> author1 = authorRepo.findById(author.getAuthor_id());

        //Assert...
        assertEquals(author1.get(), author);
    }

    @Test
    public void shouldUpdateAuthor() {
        //Arrange...
        //Set up
        Author author = new Author();
        author.setFirstName("bob");
        author.setLastName("miller");
        author.setState("TX");
        author.setCity("Austin");
        author.setStreet("Faro");
        author.setEmail("bob@gmail.com");
        author.setPostal_code("78741");
        author.setPhone("5127707160");

        //Save customer
        authorRepo.save(author);

        //Act...
        author.setFirstName("UPDATED");

        authorRepo.save(author);

        //Assert...
        Optional<Author> customer1 = authorRepo.findById(author.getAuthor_id());

        assertEquals(customer1.get(), author);
    }

    @Test
    public void shouldDeleteAuthor() {
        //Arrange...
        //Set up
        Author author = new Author();
        author.setFirstName("bob");
        author.setLastName("miller");
        author.setState("TX");
        author.setCity("Austin");
        author.setStreet("Faro");
        author.setEmail("bob@gmail.com");
        author.setPostal_code("78741");
        author.setPhone("5127707160");

        //Save customer
        authorRepo.save(author);

        //Act...
        authorRepo.deleteById(author.getAuthor_id());

        //Assert...
        Optional<Author> author1 = authorRepo.findById(author.getAuthor_id());
        assertFalse(author1.isPresent());
    }

}