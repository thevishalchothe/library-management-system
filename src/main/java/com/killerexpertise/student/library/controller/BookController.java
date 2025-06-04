package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.model.Book;
import com.killerexpertise.student.library.service.BookServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Books")
public class BookController {

    @Autowired
    BookServiceI bookServiceI;

    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book) {
        bookServiceI.createBook(book);
        return "Book added.";
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks(@RequestParam(required = false) String genre,
                               @RequestParam(required = false, defaultValue = "false") boolean available,
                               @RequestParam(required = false) String author) {
        return bookServiceI.getBooks(genre, available, author);
    }

    @PutMapping("/updateBook")
    public String updateBook(@RequestBody Book book) {
        bookServiceI.updateBook(book);
        return "Book updated";
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable int id) {
        bookServiceI.deleteBook(id);
        return "Book deleted";
    }

    @GetMapping("/getBook/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookServiceI.getBookById(id);
    }
}
