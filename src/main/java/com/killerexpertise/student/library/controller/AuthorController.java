package com.killerexpertise.student.library.controller;

import com.killerexpertise.student.library.model.Author;
import com.killerexpertise.student.library.service.AuthorServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorServiceI authorServiceI;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author) {
        authorServiceI.createAuthor(author);
        return "Author added successfully.";
    }

    @PutMapping("/update")
    public String updateAuthor(@RequestBody Author author) {
        authorServiceI.updateAuthor(author);
        return "Author updated successfully.";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable int id) {
        authorServiceI.deleteAuthor(id);
        return "Author deleted successfully.";
    }

    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        return authorServiceI.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable int id) {
        return authorServiceI.getAuthorById(id);
    }
}
