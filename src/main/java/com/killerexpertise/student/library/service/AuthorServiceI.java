package com.killerexpertise.student.library.service;

import com.killerexpertise.student.library.model.Author;

import java.util.List;

public interface AuthorServiceI {
    void createAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(int id);

    List<Author> getAllAuthors();

    Author getAuthorById(int id);
}
