package com.company.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "publisher")
public class Publisher {
    @Id
    @Column(name = "publisher_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String street;
    private String city;
    private String state;

    @Column(name = "postal_code")
    private String postal_code;
    private String phone;
    private String email;

    // One-to-Many relationship with Book
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "publisher")
    private List<Book> books;

    public Publisher() {
    }

    public Publisher(String name, String street, String city, String state, String postal_code, String phone, String email) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters for books
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    // Other getters and setters
    // ...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher)) return false;
        Publisher publisher = (Publisher) o;
        return id == publisher.id && Objects.equals(name, publisher.name) && Objects.equals(street, publisher.street) && Objects.equals(city, publisher.city) && Objects.equals(state, publisher.state) && Objects.equals(postal_code, publisher.postal_code) && Objects.equals(phone, publisher.phone) && Objects.equals(email, publisher.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, city, state, postal_code, phone, email);
    }
}
