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

---
## Core Functionalities (REST APIs) 🔐 

Below are the REST endpoints provided by the Library Management System, categorized by controller:

---

### Student Controller (`/students`) 👨‍🎓 

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/addStudent` | Add a new student (automatically creates a library card). |
| `PUT`  | `/updateStudent` | Update an existing student's information. |
| `DELETE` | `/deleteStudent?id={id}` | Delete a student by ID. |
| `GET`  | `/getAll` | Retrieve a list of all students. |
| `GET`  | `/getStudent/{id}` | Retrieve a student by their ID. |

---

### Book Controller (`/Books`) 📚 

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/addBook` | Add a new book to the library. |
| `GET`  | `/getBooks?genre=&available=&author=` | Retrieve books with optional filters: `genre`, `available` (true/false), and `author`. |
| `PUT`  | `/updateBook` | Update an existing book's information. |
| `DELETE` | `/deleteBook/{id}` | Delete a book by its ID. |
| `GET`  | `/getBook/{id}` | Get detailed information of a book by ID. |

---

### Author Controller (`/authors`) ✍️ 

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/add` | Add a new author. |
| `PUT`  | `/update` | Update an author's details. |
| `DELETE` | `/delete/{id}` | Delete an author by ID. |
| `GET`  | `/all` | Retrieve a list of all authors. |
| `GET`  | `/{id}` | Get details of a specific author by ID. |

---

### Transaction Controller (`/transactions`) 🔄 

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/issue?cardId={cardId}&bookId={bookId}` | Issue a book to a student using their card ID. Returns a unique Transaction ID. |
| `POST` | `/return?cardId={cardId}&bookId={bookId}` | Return a previously issued book. Calculates fine if applicable and returns Transaction ID. |


> ⚠️ **Note:**  

For issuing or returning a book, system will validate:
- Whether the card is activated.
- Whether the book is available (for issue).
- Book already linked with card (for return).
- Number of books issued under a card (should not exceed limit).
- Fine amount based on predefined configuration.

---



