package com.company.bookstore.repositories;

import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublisherRepositoryTest {
    @Autowired
    private PublisherRepository publisherRepository;


    private Publisher testPublisher = new Publisher("z", "south", "LA", "CA", "90909", "111-111-1111", "z@hotmail.com");

    @BeforeEach
    void setUp() {
        publisherRepository.deleteAll();
        testPublisher = publisherRepository.save(testPublisher);

    }
//    Create
    @Test
    void shouldAddPublisher(){
        Optional<Publisher> expected = publisherRepository.findById(testPublisher.getId());
        assertEquals(expected.get(), testPublisher);
    }
//    Read all
    @Test
    void shouldFindPublishers(){
        List<Publisher> expected = new ArrayList<>();
        expected.add(testPublisher);
        assertEquals(publisherRepository.findAll(),expected);
    }
//    read byID
    @Test
    void shouldFindPublisherByID(){
        Optional<Publisher> expected = publisherRepository.findById(testPublisher.getId());
        assertEquals(expected.get(),testPublisher);
    }
//    update
    @Test
    void shouldUpdatePublisher(){
        Publisher updated = new Publisher("z", "south", "LA", "CA", "90909", "111-111-1111", "z@hotmail.com");
        updated.setId(testPublisher.getId());
        assertEquals(updated, publisherRepository.findById(testPublisher.getId()).get());
    }
//    Delete
    @Test
    void shouldDeletePublisher(){
        Optional<Publisher> optionalPublisher;


        publisherRepository.delete(testPublisher);

        optionalPublisher = publisherRepository.findById(testPublisher.getId());

        assertFalse(optionalPublisher.isPresent(), "publisher should be deleted");

    }

}