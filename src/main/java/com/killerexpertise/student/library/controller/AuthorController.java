package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.model.Author;
import com.killerexpertise.student.library.service.AuthorServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorServiceI authorServiceI;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {
        authorServiceI.createAuthor(author);
        return new ResponseEntity<>("Author added successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorServiceI.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        Author author = authorServiceI.getAuthorById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAuthor(@RequestBody Author author) {
        authorServiceI.updateAuthor(author);
        return new ResponseEntity<>("Author updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id) {
        authorServiceI.deleteAuthor(id);
        return new ResponseEntity<>("Author deleted successfully.", HttpStatus.OK);
    }
}
