package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.AuthorRepository;
import com.company.bookstore.repositories.PublisherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Parameter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private AuthorRepository repo;

    Author author1 = new Author("m", "pugh", "main", "longbeach", "CA", "90803", "314-299-3259", "mp@gmail.com");



    //New Author


    @BeforeEach
    public void setup() throws Exception{
        repo.deleteAll();
        author1 = new Author("m", "pugh", "main", "longbeach", "CA", "90803", "314-299-3259", "mp@gmail.com");
        repo.save(author1);

    }

    @Test
    public void shouldAddAuthor() throws Exception {
        Author a = new Author("bob", "miller", "Faro", "Austin", "TX", "78741", "5127707160", "frankie.ortiz2001@gmail.com");
        String inputJson = mapper.writeValueAsString(a);

        mockMvc.perform(
                        post("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldGetAuthors() throws Exception {
        mockMvc.perform(get("/authors"))
                .andDo(print())             //Print results
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAuthorById() throws Exception {
        mockMvc.perform(get("/authors/{id}", author1.getAuthor_id()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateAuthor() throws Exception {
        Author a = new Author("bob", "timmy", "Faro", "Houston", "TX", "78741", "5127707160", "frankie.ortiz2001@gmail.com");
        String inputJson = mapper.writeValueAsString(a);

        mockMvc.perform(put("/authors") .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }


    @Test
    void shouldDeleteAuthor() throws Exception {
        author1.setAuthor_id(1);
        mockMvc.perform(delete("/authors/{id}", author1.getAuthor_id()))
                .andDo(print())
                .andExpect(status().isNoContent());

    }




}