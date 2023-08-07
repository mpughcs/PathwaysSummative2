package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.AuthorRepository;
import com.company.bookstore.repositories.BookRepository;
import com.company.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    AuthorRepository authorRepository;

//    find book by id
//
    @QueryMapping
    public Book findBookById(@Argument int id){
        Optional<Book> toReturn = bookRepository.findById(id);
        if (toReturn.isPresent()) {
            return toReturn.get();
        }else {
            return null;
        }
    }
    @QueryMapping
    public Author findAuthorById(@Argument int id){
        Optional<Author> toReturn = authorRepository.findById(id);
        if (toReturn.isPresent()) {
            return toReturn.get();
        }else {
            return null;
        }

    }


    // find publisher by id
    @QueryMapping
    public Publisher findPublisherById(@Argument int id){
        Optional<Publisher> toReturn = publisherRepository.findById(id);
        if (toReturn.isPresent()) {
            return toReturn.get();
        } else {
            return null;
        }
    }
    @SchemaMapping
    public Author author(Book book) {
        Optional<Author> returnVal = authorRepository.findById(book.getAuthorId().getAuthor_id());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }
    @SchemaMapping
    public Publisher publisher(Book book) {
        Optional<Publisher> returnVal = publisherRepository.findById(book.getPublisherId().getId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }
    @SchemaMapping
    public List<Book> books(Author author) {
        return bookRepository.findByAuthorId(author.getAuthor_id().intValue());
    }




}
