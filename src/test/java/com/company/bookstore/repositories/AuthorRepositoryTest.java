package com.company.bookstore.repositories;

import com.company.bookstore.models.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
 class AuthorRepositoryTest {


    @Autowired
    AuthorRepository authorRepo;

//    @BeforeEach
//    public void setUp() throws Exception {
//        authorRepo.deleteAll();
//    }

    @Test
    public void addAuthor() {
        //Set up
        Author author = new Author();
        author.setFirstName("bob");
        author.setLastName("miller");
        author.setState("Texas");

        //Save customer
        author = authorRepo.save(author);

        //Assert
        Optional<Author> author1 = authorRepo.findById(author.getAuthor_id());

        assertEquals(author1.get(), author);
    }
}