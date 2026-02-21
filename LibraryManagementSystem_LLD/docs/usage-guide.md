# 📖 Usage Guide

## 1. Running the Application
- If using Spring Boot:
  ./mvnw spring-boot:run  
  or  
  ./gradlew bootRun

- The application will start on http://localhost:8080 by default.

---

## 2. REST API Endpoints

### BookController
- POST /books → Add a new book
- PUT /books/{id} → Update book details
- DELETE /books/{id} → Remove a book
- GET /books → List all books
- GET /books?title=...&author=...&isbn=... → Search books

### PatronController
- POST /patrons → Register a new patron
- PUT /patrons/{id} → Update patron info
- GET /patrons → List all patrons
- GET /patrons/{id}/history → Get borrowing history

### LendingController
- POST /lendings/checkout → Checkout a book
- POST /lendings/return → Return a book
- GET /lendings → View all lending records

### ReservationController
- POST /reservations → Reserve a book
- GET /reservations/{patronId} → View reservations for a patron

### RecommendationController
- GET /recommendations/{patronId}?strategy=history → History-based recommendations
- GET /recommendations/{patronId}?strategy=genre → Genre-based recommendations

---

## 3. Example Execution with PowerShell

```powershell
# 1. Add a Book
Invoke-RestMethod -Uri "http://localhost:8080/books" `
  -Method POST `
  -Headers @{ "Content-Type" = "application/json" } `
  -Body '{"id":1,"title":"1984","author":"George Orwell","isbn":"1234567890","publicationYear":1949}'

# 2. Add a Patron
Invoke-RestMethod -Uri "http://localhost:8080/patrons" `
  -Method POST `
  -Headers @{ "Content-Type" = "application/json" } `
  -Body '{"id":101,"name":"Alice","email":"alice@example.com"}'

# 3. Checkout the Book
Invoke-RestMethod -Uri "http://localhost:8080/lendings/checkout" `
  -Method POST `
  -Headers @{ "Content-Type" = "application/json" } `
  -Body '{"bookId":1,"patronId":101}'

# 4. Return the Book
Invoke-RestMethod -Uri "http://localhost:8080/lendings/return" `
  -Method POST `
  -Headers @{ "Content-Type" = "application/json" } `
  -Body '{"bookId":1,"patronId":101}'

# 5. View All Lending Records
Invoke-RestMethod -Uri "http://localhost:8080/lendings" -Method GET

# 6. View All Patrons
Invoke-RestMethod -Uri "http://localhost:8080/patrons" -Method GET

# 7. View Patron Borrowing History
Invoke-RestMethod -Uri "http://localhost:8080/patrons/101/history" -Method GET

# 8. View All Books
Invoke-RestMethod -Uri "http://localhost:8080/books" -Method GET

# 9. Place a Reservation
Invoke-RestMethod -Uri "http://localhost:8080/reservations" `
  -Method POST `
  -Headers @{ "Content-Type" = "application/json" } `
  -Body '{"isbn":"1234567890","patronId":101}'

# 10. Get Recommendations
Invoke-RestMethod -Uri "http://localhost:8080/recommendations/101?strategy=history" -Method GET
```
## 7. Testing
- Use Postman or PowerShell to test endpoints.

- Logs will show events like book checkout, return, and reservation notifications.

---
## 7. Notes
- All endpoints return JSON responses.

- Logs will show events like book checkout, return, and reservation notifications.

- Error handling is logged using SLF4J or java.util.logging.

- Extendable for persistence (databases, APIs) in future iterations.
---

## 6. Design Pattern Execution
- Observer Pattern (Reservation Notifications)

    Patrons register as observers when reserving a book.
    When the book is returned, ReservationService triggers notifyAvailability.
    All registered observers (e.g., PatronReservationObserver) receive a notification.

- Strategy Pattern (Recommendations)

    The system supports interchangeable recommendation algorithms.
    Query parameter strategy determines which algorithm is used:
    history → Suggest books by authors previously borrowed.
    genre → Suggest books based on patron’s preferred genres.
    New strategies (e.g., popularity-based) can be added without modifying existing code.

---

## 7. Notes on Patterns
- Observer Pattern: Decouples reservation notifications from lending logic. New observers can be added without changing core services.

- Strategy Pattern: Makes recommendation algorithms interchangeable and extensible. Adding a new strategy requires no changes to existing code.
---