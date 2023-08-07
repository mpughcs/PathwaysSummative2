package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.AuthorRepository;
import com.company.bookstore.repositories.BookRepository;
import com.company.bookstore.repositories.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//ask in office hours, if this is alright, had to change application properties ddl from none to update
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository pubRepository;

    @Autowired
    private BookRepository repo;

    private Author author1 = new Author("m", "pugh", "main", "longbeach", "CA", "90803", "314-299-3259", "mp@gmail.com");
    private Publisher publisher1 = new Publisher("z", "south", "LA", "CA", "90909", "111-111-1111", "z@hotmail.com");
    Book b = new Book("100000000000", LocalDate.now(), author1, "lion book", publisher1, new BigDecimal("12.99"));
    String inputJson;
    @BeforeEach
    public void setup() throws Exception {
        repo.deleteAll();
        authorRepository.deleteAll();
        pubRepository.deleteAll();

        author1 = authorRepository.save(author1);
        publisher1 = pubRepository.save(publisher1);
        inputJson = mapper.writeValueAsString(b);
        b=repo.save(b);

    }
    @AfterEach
    void cleanUp(){
        repo.deleteAll();
        authorRepository.deleteAll();
        pubRepository.deleteAll();
    }


    @Test
    void shouldAddBook() throws Exception {
//        inputJson = mapper.writeValueAsString(b);

        mockMvc.perform(
                        post("/books")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }



    @Test
    void shouldGetBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetBooksById() throws Exception {
        mockMvc.perform(get("/books/{id}",b.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateBook() throws Exception {
        mockMvc.perform(
                        put("/books/", b)
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteBook() throws Exception {
        repo.save(b);
        mockMvc.perform(delete("/books/{id}", b.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldFindByAuthorId() throws Exception{
        repo.save(b);
        mockMvc.perform(get("/books/authorId/{id}",b.getAuthorId().getAuthor_id()))
                .andDo(print())
                .andExpect(status().isOk());

    }
}