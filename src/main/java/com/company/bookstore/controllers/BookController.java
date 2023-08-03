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

//    Create

        @PostMapping("/books")
        @ResponseStatus(HttpStatus.CREATED)
        public Book addBook(@RequestBody Book toAdd){return repo.save(toAdd);}



//    read
    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooks(){return repo.findAll();}

//    Read by id
    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBooksById(@PathVariable int id){
        Optional<Book> toReturn = repo.findById(id);
        if(toReturn.isPresent()){
            return toReturn.get();
        } else{
            return null;
        }
    }
//    update
    @PutMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book){
        repo.save(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id){
        repo.deleteById(id);
    }
//custom query
    @GetMapping("/books/authorId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findByAuthorId(@PathVariable int id){
        List<Book> toReturn = new ArrayList<>();
        for(Book book : repo.findAll()){
            if (book.getAuthorId().getAuthor_id() == id){
                toReturn.add(book);
            }
        }
        return toReturn;
    }

}
