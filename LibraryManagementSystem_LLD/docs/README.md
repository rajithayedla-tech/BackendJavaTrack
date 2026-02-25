# 📚 Library Management System (LMS)

## Overview
This project is a **Java-based Library Management System** designed to help librarians manage books, patrons, lending, reservations, and recommendations.  
It demonstrates **Object-Oriented Programming (OOP)**, **SOLID principles**, and the use of **design patterns** such as Strategy and Observer.

---

## Features
- **Book Management:** Add, remove, update, and search books by title, author, or ISBN.
- **Patron Management:** Register patrons, update information, and track borrowing history.
- **Lending Process:** Checkout and return books with inventory updates.
- **Inventory Management:** Track available vs. borrowed books.
- **Reservation System (Observer Pattern):** Patrons can reserve books and receive notifications when available.
- **Recommendation System (Strategy Pattern):** Suggest books based on borrowing history or genre preferences.
- **Multi-branch Support:** Manage multiple library branches and transfer books between them.

---

## Technical Highlights
- **OOP Concepts:** Encapsulation, inheritance, polymorphism, abstraction.
- **SOLID Principles:**
    - Single Responsibility → Each service handles one domain.
    - Open/Closed → Extend recommendations without modifying existing code.
    - Liskov Substitution → Strategies interchangeable via interface.
    - Interface Segregation → Separate observer/recommendation interfaces.
    - Dependency Inversion → Controllers depend on abstractions, not implementations.
- **Design Patterns:**
    - *Observer* → Reservation notifications.
    - *Strategy* → Recommendation system.
- **Collections:** Lists, Sets, Maps for inventory, reservations, and histories.
- **Logging:** SLF4J or `java.util.logging` for events and errors.

---

## How to Run
- git clone https://github.com/your-username/library-management-system.git
- javac -d bin src/main/java/com/airtribe/lms/**/*.java
- java com.airtribe.lms.controller.BookController

---

### 📊 Class Diagram

```mermaid
classDiagram
    Book <|-- LendingRecord
    Patron --> LendingRecord
    ReservationObserver <|.. PatronReservationObserver
    RecommendationStrategy <|.. GenreBasedRecommendation
    RecommendationStrategy <|.. HistoryBasedRecommendation
