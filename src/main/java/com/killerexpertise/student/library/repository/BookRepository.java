package com.killerexpertise.student.library.repository;

import com.killerexpertise.student.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Find books by genre and availability
    List<Book> findByGenreAndAvailable(String genre, boolean available);

    // Find books by author name and availability
    List<Book> findByAuthor_NameAndAvailable(String authorName, boolean available);

    // Find books by genre, author name and availability
    List<Book> findByGenreAndAuthor_NameAndAvailable(String genre, String authorName, boolean available);

    // Find books by availability only
    List<Book> findByAvailable(boolean available);
}
