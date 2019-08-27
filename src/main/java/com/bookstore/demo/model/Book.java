package com.bookstore.demo.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "books")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_books")
    private Integer idBooks;

    @Column(name = "books_name")
    @NotBlank
    private String booksName;

    @Column(name = "books_writers")
    @NotBlank
    private String booksWriter;

    @Column(name = "books_publisher")
    @NotBlank
    private String booksPublisher;

    public Integer getIdBooks() {
        return idBooks;
    }

    public void setIdBooks(Integer idBooks) {
        this.idBooks = idBooks;
    }

    public String getBooksName() {
        return booksName;
    }

    public void setBooksName(String booksName) {
        this.booksName = booksName;
    }

    public String getBooksWriter() {
        return booksWriter;
    }

    public void setBooksWriter(String booksWriter) {
        this.booksWriter = booksWriter;
    }

    public String getBooksPublisher() {
        return booksPublisher;
    }

    public void setBooksPublisher(String booksPublisher) {
        this.booksPublisher = booksPublisher;
    }
}
