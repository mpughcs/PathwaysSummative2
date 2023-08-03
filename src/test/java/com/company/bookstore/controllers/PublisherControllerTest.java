package com.company.bookstore.controllers;

import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublisherController.class)
class PublisherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private PublisherRepository repo;

    @BeforeEach
    public void setup() throws Exception{
        repo.deleteAll();
    }
    @Test
    void addPublisher() throws Exception {
        Publisher p = new Publisher("testPub","main","LA","CA","90802","123","testemail");
        String inputJson = mapper.writeValueAsString(p);

        mockMvc.perform(
                        post("/publishers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getPublishers() {
    }

    @Test
    void getPublisherById() {
    }

    @Test
    void updatePublisher() {
    }

    @Test
    void deletePublisher() {
    }
}