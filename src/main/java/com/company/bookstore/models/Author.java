package com.company.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer author_id;

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String phone;
    private String email;

    // One-to-Many relationship with Book
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Author(String firstName, String lastName, String street, String city, String state, String postal_code, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
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
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(author_id, author.author_id) && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(street, author.street) && Objects.equals(city, author.city) && Objects.equals(state, author.state) && Objects.equals(postal_code, author.postal_code) && Objects.equals(phone, author.phone) && Objects.equals(email, author.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author_id, firstName, lastName, street, city, state, postal_code, phone, email);
    }
}
