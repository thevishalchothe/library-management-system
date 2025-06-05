package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.model.Book;
import com.killerexpertise.student.library.service.BookServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Books")
public class BookController {

    @Autowired
    private BookServiceI bookServiceI;

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        bookServiceI.createBook(book);
        return new ResponseEntity<>("Book added.", HttpStatus.OK);
    }

    @GetMapping("/getBooks")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String genre,
                                               @RequestParam(required = false, defaultValue = "false") boolean available,
                                               @RequestParam(required = false) String author) {
        List<Book> books = bookServiceI.getBooks(genre, available, author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        bookServiceI.updateBook(book);
        return new ResponseEntity<>("Book updated", HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookServiceI.deleteBook(id);
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookServiceI.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
