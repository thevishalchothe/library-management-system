package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.model.Author;
import com.killerexpertise.student.library.service.AuthorServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorServiceI authorServiceI;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {
        authorServiceI.createAuthor(author);
        return ResponseEntity.ok("Author added successfully.");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAuthor(@RequestBody Author author) {
        authorServiceI.updateAuthor(author);
        return ResponseEntity.ok("Author updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id) {
        authorServiceI.deleteAuthor(id);
        return ResponseEntity.ok("Author deleted successfully.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorServiceI.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        Author author = authorServiceI.getAuthorById(id);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
