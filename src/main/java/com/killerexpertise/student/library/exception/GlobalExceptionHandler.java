package com.killerexpertise.student.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAuthorNotFound(AuthorNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Author Not Found");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBookNotFound(BookNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Book Not Found");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleStudentNotFound(StudentNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Student Not Found");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookUnavailableException.class)
    public ResponseEntity<Map<String, Object>> handleBookUnavailable(BookUnavailableException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, "Book Unavailable");
    }

    @ExceptionHandler(CardInvalidException.class)
    public ResponseEntity<Map<String, Object>> handleCardInvalid(CardInvalidException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, "Card Invalid");
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTransactionNotFound(TransactionNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, "Transaction Not Found");
    }

    // Utility method
    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status, String errorType) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", message);
        error.put("status", status.value());
        error.put("error", errorType);
        return new ResponseEntity<>(error, status);
    }

    // Optional: handle other generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
