# Spring Boot Library Management System 📖📚🔥

Welcome to the `Spring Boot Library Management System`: a robust backend application designed to streamline library operations. 

This system allows managing books, authors, publishers, categories, and users, while efficiently handling borrowing and returning of books with real-time inventory tracking. Built with Spring Boot, Spring Data JPA, and MySQL, it follows a clean RESTful architecture to deliver reliable library management.

**Manage books, users, and borrowings effortlessly!** ✨🚀

---

##  Key Features ✍️ 📝

- **Author Management** 🔐 
  - Add new authors
  - Update author details
  - Delete authors
  - View single or all authors

- **Book Management** 📘 
  - Add new books with genre, author, availability status, etc.
  - Update book information
  - Delete books
  - Filter books by genre, availability, or author
  - View book by ID or list all books

- **Student Management** 🎓 
  - Register students
  - Update student details
  - Delete students
  - Get details of a student or list all students

- **Book Transaction** 🔄 
  - Issue a book to a student (using Card ID and Book ID)
  - Return a book
  - Generate a unique transaction ID for each operation

- **Database-First with JPA + Lombok** 🔒 
  - Uses JPA annotations for entity mapping.
  - Reduces boilerplate code using Lombok (`@Data`, `@NoArgsConstructor`, etc.).
