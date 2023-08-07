package com.company.bookstore.controllers;

import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {

    @Autowired
    PublisherRepository repo;

    // Create
    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher toAdd) {
        // Ensure the correct books are set for the publisher
        List<Book> books = toAdd.getBooks();
        if (books != null) {
            for (Book book : books) {
                Optional<Book> optionalBook = repo.findById(book.getId());
                if (optionalBook.isPresent()) {
                    book.setPublisherId(toAdd);
                } else {
                    book.setPublisherId(null); // Invalid Book, set to null
                }
            }
        }

        return repo.save(toAdd);
    }

    // Read
    @GetMapping("/publishers")
    @ResponseStatus(HttpStatus.OK)
    public List<Publisher> getPublishers() {
        return repo.findAll();
    }

    // Read by id
    @GetMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher getPublisherById(@PathVariable int id) {
        Optional<Publisher> toReturn = repo.findById(id);
        return toReturn.orElse(null);
    }
//
//    // Update
//    @PutMapping("/publishers/")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updatePublisher(@RequestBody Publisher pub) {
//        // Ensure the correct books are set for the publisher
//        List<Book> books = pub.getBooks();
//        if (books != null) {
//            for (Book book : books) {
//                Optional<Book> optionalBook = repo.findById(book.getId());
//                if (optionalBook.isPresent()) {
//                    book.setPublisherId(pub);
//                } else {
//                    book.setPublisherId(null); // Invalid Book, set to null
//                }
//            }
//        }
//
//        repo.save(pub);
//    }

    // Delete
    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id) {
        repo.deleteById(id);
    }
}
