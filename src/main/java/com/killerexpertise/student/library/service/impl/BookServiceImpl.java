package com.killerexpertise.student.library.service.impl;

import com.killerexpertise.student.library.model.Book;
import com.killerexpertise.student.library.repository.BookRepository;
import com.killerexpertise.student.library.service.BookServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookServiceI {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Book book) {
        // Save acts as insert or update if ID exists
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooks(String genre, boolean available, String author) {
        if (genre != null && author != null) {
            return bookRepository.findByGenreAndAuthor_NameAndAvailable(genre, author, available);
        } else if (genre != null) {
            return bookRepository.findByGenreAndAvailable(genre, available);
        } else if (author != null) {
            return bookRepository.findByAuthor_NameAndAvailable(author, available);
        } else {
            return bookRepository.findByAvailable(available);
        }
    }

    @Override
    public Book getBookById(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }
}
