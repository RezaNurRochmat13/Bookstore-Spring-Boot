package com.bookstore.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore.demo.model.Book;
import com.bookstore.demo.repository.BookRepository;
import com.bookstore.demo.exception.ResourceNotFoundException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping("/public/api/v1/")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("books")
    public Map getAllBooks() {
        Map mappingObjects = new HashMap();
        List<Book> booksList = bookRepository.findAll();

        mappingObjects.put("count", booksList.size());
        mappingObjects.put("data", booksList);
        mappingObjects.put("total", booksList.size());
        return mappingObjects;
    }

    @GetMapping("books/{id}")
    public Map getDetailBooks(@PathVariable(value = "id") Integer bookId) {
        Map mappingObjectBookDetail = new HashMap();
        Optional<Book> bookDetail = bookRepository.findById(bookId);

        if (bookDetail.isPresent()) {
            mappingObjectBookDetail.put("data", bookDetail);
        } else {
            mappingObjectBookDetail.put("data", "Kosong");
        }
        return mappingObjectBookDetail;
    }

    @PostMapping("books")
    public Book createNewBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("books/{id}")
    public Book updateBook(@PathVariable(value = "id") Integer bookId,
                           @Valid @RequestBody Book updateBook) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Books", "id", bookId));

        book.setBooksName(updateBook.getBooksName());
        book.setBooksWriter(updateBook.getBooksWriter());
        book.setBooksPublisher(updateBook.getBooksPublisher());

        Book savedUpdateBook = bookRepository.save(book);

        return savedUpdateBook;

    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Integer bookId) {
        Book findBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Books", "id", bookId));

        bookRepository.delete(findBook);

        return ResponseEntity.ok().build();
    }
}
