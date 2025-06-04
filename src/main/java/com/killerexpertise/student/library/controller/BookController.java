package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.model.Book;
import com.killerexpertise.student.library.service.BookServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Books")
public class BookController {

    @Autowired
    BookServiceI bookServiceI;

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        bookServiceI.createBook(book);
        return ResponseEntity.ok("Book added.");
    }

    @GetMapping("/getBooks")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String genre,
                                               @RequestParam(required = false, defaultValue = "false") boolean available,
                                               @RequestParam(required = false) String author) {
        List<Book> books = bookServiceI.getBooks(genre, available, author);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        bookServiceI.updateBook(book);
        return ResponseEntity.ok("Book updated");
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookServiceI.deleteBook(id);
        return ResponseEntity.ok("Book deleted");
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookServiceI.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
