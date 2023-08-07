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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    Publisher p;
    String inputJson;
    @BeforeEach
    public void setup() throws Exception{
        repo.deleteAll();
        p = new Publisher("testPub","main","LA","CA","90802","123","testemail");
        inputJson = mapper.writeValueAsString(p);

    }
    @Test
    void shouldAddPublisher() throws Exception {
        mockMvc.perform(
                        post("/publishers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetPublishers() throws Exception {
        mockMvc.perform(get("/publishers"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetPublisherById() throws Exception {
        mockMvc.perform(get("/publishers/{id}",p.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdatePublisher() throws Exception {
        mockMvc.perform(
                        put("/publishers/", p)
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeletePublisher() throws Exception {
        repo.save(p);
        mockMvc.perform(delete("/publishers/{id}", p.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

}