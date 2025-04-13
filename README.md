# Binary Search Tree Web Application

This is a Spring Boot web application that allows users to create and visualize a Binary Search Tree (BST) from user input, view the result, and store/retrieve past tree structures from a MySQL database.

---

## 📦 Project Overview

This project is structured to:

- Allow user input of comma-separated numbers
- Build a **balanced** BST from those numbers
- Store the input and tree structure in a database
- Display the tree visually on the screen
- Show a history of previously submitted trees

---

## 🚀 How to Start the Project

### Prerequisites

- Java 21+
- Maven
- MySQL running locally (or updated DB connection details)
- IDE (e.g., IntelliJ, VS Code) or command-line

### Steps

1. Clone or download the project
2. Open terminal in the project root
3. Start the app:

```bash
./mvnw spring-boot:run
```

4. Open your browser and go to:

```
http://localhost:8080/enter-numbers
```

---

## 🧩 Key Functionalities

### 🔷 1. Input Interface

Route:
```
GET /enter-numbers
```

- HTML form to enter numbers
- Buttons to submit tree or view history

---

### 🔷 2. Tree Processing

Route:
```
POST /process-numbers
```

- Parses and sorts user input
- Builds a balanced BST from the sorted list
- Converts tree to JSON and stores it in the database
- Shows a **visual tree** on the result page

---

### 🔷 3. View Previous Trees

Route:
```
GET /api/tree/previous
```

- Retrieves all past input/trees from DB
- Displays them in a table with ID, numbers, and JSON tree

---

## 🗃️ Database Functionality

- Stores input numbers and the tree structure as JSON
- Uses JPA with Spring Data
- Table: `tree_record`
  - `id`: Auto-generated ID
  - `input_numbers`: Original input (e.g., `1,2,3`)
  - `tree_json`: Stored tree in JSON format

---

## ✅ Testing

3 unit tests are included:

- BST insertion logic
- Tree-to-JSON conversion
- Repository save/retrieve with test database

Run them using:

```bash
./mvnw test
```

---
