# Library Management System 📖📚🔥

Welcome to the **Library Management System** – a comprehensive backend application built using **Spring Boot**, designed to efficiently manage library operations in a college environment.

This system allows students to register, issue books, return them, and track their transactions. It also enforces core library rules such as:

- Maximum book issuance limits
- Late return fines
- Card activation/deactivation status

The project follows a clean **Monolithic Architecture**, with a well-structured layering of:

- **Controller** – Handles API endpoints
- **Service** – Contains business logic
- **Repository** – Interacts with the database
- **Model** – Represents the data/entities

Built with performance, maintainability, and scalability in mind, this application provides a solid foundation for managing student-library interactions.

---

## Backend Design: Entities 📄 

### Entity Overview

| Entity      | Description |
|-------------|-------------|
| **Student** | Represents library users. Contains fields like `id`, `name`, `email`, and is associated with a `Card`. |
| **Card**    | Linked to a student. Tracks `status` (ACTIVATED/DEACTIVATED), `createdOn`, `updatedOn`, and maintains references to issued `Books` and `Transactions`. |
| **Book**    | Represents a library book. Contains fields such as `id`, `title`, `genre`, `author`, and an `isAvailable` flag to indicate availability. |
| **Author**  | Represents book authors. Contains fields like `name`, `email`, and `country`. Each `Book` is linked to one `Author`. |
| **Transaction** | Records every book issue or return operation. Tracks `transactionId` (UUID), `date`, `card`, `book`, `fineAmount`, `isIssueOperation` (true for issue, false for return), and `transactionStatus` (e.g. SUCCESSFUL). |

---

##  Backend Design: Key Features ✍️ 📝

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

