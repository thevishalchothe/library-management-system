package com.killerexpertise.student.library.service.impl;

import com.killerexpertise.student.library.model.Author;
import com.killerexpertise.student.library.repository.AuthorRepository;
import com.killerexpertise.student.library.service.AuthorServiceI;
import com.killerexpertise.student.library.exception.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (!authorRepository.existsById(author.getId())) {
            throw new AuthorNotFoundException("Author with ID " + author.getId() + " not found for update.");
        }
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(int id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found for deletion.");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(int id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + id + " not found."));
    }
}
