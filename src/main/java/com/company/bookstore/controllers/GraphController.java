package com.company.bookstore.controllers;

import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.AuthorRepository;
import com.company.bookstore.repositories.BookRepository;
import com.company.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

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
    @QueryMapping
    public Book findBookById(@Argument int id){
        Optional<Book> toReturn = bookRepository.findById(id);
        if (toReturn.isPresent()) {
            return toReturn.get();
        } else {
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





}
