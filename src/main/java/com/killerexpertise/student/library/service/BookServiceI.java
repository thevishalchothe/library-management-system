package com.killerexpertise.student.library.service;

import com.killerexpertise.student.library.model.Book;

import java.util.List;

public interface BookServiceI {
    void createBook(Book book);

    List<Book> getBooks(String genre, boolean available, String author);

    void updateBook(Book book);

    void deleteBook(int id);

    Book getBookById(int id);
}
