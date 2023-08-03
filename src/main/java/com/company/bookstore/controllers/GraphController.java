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
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            Book toReturn= book.get();
            Author author = authorRepository.findById(toReturn.getAuthorId().getAuthor_id()).get();
            Publisher publisher=publisherRepository.findById(toReturn.getPublisherId().getId()).get();
            toReturn.setAuthorId(author);
            toReturn.setPublisherId(publisher);
            return toReturn;
        }
        return null;

    }
//    @QueryMapping
//    public Book findBookById(@Argument int id){
//        Optional<Book> toReturn = bookRepository.findById(id);
//        if (toReturn.isPresent()) {
//            return toReturn.get();
//        }else {
//            return null;
//        }
//
//    }

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
    public Author author (Book book) {
        Optional<Author> returnVal = authorRepository.findById(book.getId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        }else {
            return null;

        }
    }






}
