package com.company.bookstore.controllers;

import com.company.bookstore.models.Book;
import com.company.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookRepository repo;

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
    public Book getCustomerById(@PathVariable int id){
        Optional<Book> toReturn = repo.findById(id);
        if(toReturn.isPresent()){
            return toReturn.get();
        } else{
            return null;
        }
    }
//    update
    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody Book book, @PathVariable int id){
        repo.save(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id){
        repo.deleteById(id);
    }
//custom query
@GetMapping("/book/authorId/{id}")
@ResponseStatus(HttpStatus.OK)
public List<Book> findByState(@PathVariable int id){
    return repo.findByAuthorId(id);
}

}
