package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository repo;

    // Create
    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author) {
        // Make sure to set the author for each associated book
        List<Book> books = author.getBooks();
        if (books != null) {
            for (Book book : books) {
                book.setAuthorId(author);
            }
        }
        return repo.save(author);
    }

    // Read by ID
    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable int id) {
        Optional<Author> returnVal = repo.findById(id);
        return returnVal.orElse(null);
    }

    // Read All
    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return repo.findAll();
    }

    // Update
    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Author author) {
        // Make sure to update the author for each associated book
        List<Book> books = author.getBooks();
        if (books != null) {
            for (Book book : books) {
                book.setAuthorId(author);
            }
        }
        repo.save(author);
    }

    // Delete
    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id) {
        // Before deleting the author, make sure to remove the association with the books
        Optional<Author> optionalAuthor = repo.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            List<Book> books = author.getBooks();
            if (books != null) {
                for (Book book : books) {
                    book.setAuthorId(null);
                }
            }
        }
        repo.deleteById(id);
    }
}
