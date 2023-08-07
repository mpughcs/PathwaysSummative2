package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.AuthorRepository;
import com.company.bookstore.repositories.BookRepository;
import com.company.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository repo;

    @Autowired
    AuthorRepository aRepo;

    @Autowired
    PublisherRepository pRepo;

    // Create
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book toAdd) {
        // Ensure the correct Author and Publisher are set for the book
        Author author = toAdd.getAuthorId();
        if (author != null) {
            Optional<Author> optionalAuthor = aRepo.findById(author.getAuthorid());
            if (optionalAuthor.isPresent()) {
                toAdd.setAuthor(optionalAuthor.get());
            } else {
                toAdd.setAuthor(null); // Invalid Author, set to null
            }
        }

        Publisher publisher = toAdd.getPublisher();
        if (publisher != null) {
            Optional<Publisher> optionalPublisher = pRepo.findById(publisher.getId());
            if (optionalPublisher.isPresent()) {
                toAdd.setPublisher(optionalPublisher.get());
            } else {
                toAdd.setPublisher(null); // Invalid Publisher, set to null
            }
        }

        return repo.save(toAdd);
    }

    // Read
    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooks() {
        return repo.findAll();
    }

    // Read by id
    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBooksById(@PathVariable int id) {
        Optional<Book> toReturn = repo.findById(id);
        return toReturn.orElse(null);
    }

    // Update
    @PutMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book) {
        // Ensure the correct Author and Publisher are set for the book
        Author author = book.getAuthor();
        if (author != null) {
            Optional<Author> optionalAuthor = aRepo.findById(author.getAuthor_id());
            if (optionalAuthor.isPresent()) {
                book.setAuthor(optionalAuthor.get());
            } else {
                book.setAuthor(null); // Invalid Author, set to null
            }
        }

        Publisher publisher = book.getPublisher();
        if (publisher != null) {
            Optional<Publisher> optionalPublisher = pRepo.findById(publisher.getId());
            if (optionalPublisher.isPresent()) {
                book.setPublisher(optionalPublisher.get());
            } else {
                book.setPublisher(null); // Invalid Publisher, set to null
            }
        }

        repo.save(book);
    }

    // Delete
    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        repo.deleteById(id);
    }

    // Custom query to find books by author ID

}
