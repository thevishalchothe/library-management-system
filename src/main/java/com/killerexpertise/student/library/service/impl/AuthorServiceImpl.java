package com.killerexpertise.student.library.service.impl;

import com.killerexpertise.student.library.model.Author;
import com.killerexpertise.student.library.repository.AuthorRepository;
import com.killerexpertise.student.library.service.AuthorServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorServiceI {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void createAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Author author) {
        // Save acts as update if ID is present
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(int id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.orElse(null);
    }
}
