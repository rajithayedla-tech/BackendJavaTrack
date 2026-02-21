# 📖 Usage Guide

## 1. Running the Application
- If using Spring Boot:
  ./mvnw spring-boot:run
  or
  ./gradlew bootRun

- The application will start on http://localhost:8080 by default.

---

## 2. REST API Endpoints

BookController
- POST /books → Add a new book
- PUT /books/{isbn} → Update book details
- DELETE /books/{isbn} → Remove a book
- GET /books?title=...&author=...&isbn=... → Search books

PatronController
- POST /patrons → Register a new patron
- PUT /patrons/{id} → Update patron info
- GET /patrons/{id}/history → Get borrowing history

LendingController
- POST /lendings/checkout → Checkout a book
- POST /lendings/return → Return a book

ReservationController
- POST /reservations → Reserve a book
- GET /reservations/{patronId} → View patron reservations

RecommendationController
- GET /recommendations/{patronId}?strategy=history → History-based recommendations
- GET /recommendations/{patronId}?strategy=genre → Genre-based recommendations

---

## 3. Example Execution with curl

Add a book:
curl -X POST http://localhost:8080/books \
-H "Content-Type: application/json" \
-d '{"title":"1984","author":"George Orwell","isbn":"1234567890","publicationYear":1949}'

Checkout a book:
curl -X POST http://localhost:8080/lendings/checkout \
-H "Content-Type: application/json" \
-d '{"isbn":"1234567890","patronId":"P001"}'

Get recommendations:
curl http://localhost:8080/recommendations/P001?strategy=history

---

## 4. Testing
- Use Postman or curl to test endpoints.
- Logs will show events like book checkout, return, and reservation notifications.

---

## 5. Notes
- All endpoints return JSON responses.
- Error handling is logged using SLF4J or java.util.logging.
- Extendable for persistence (databases, APIs) in future iterations.

---

## 6. Design Pattern Execution

### Observer Pattern (Reservation Notifications)

Concept:
The Observer pattern is used so that when a book becomes available, all patrons who reserved it are notified automatically.

Flow:
1. A patron reserves a book via POST /reservations.
2. The book is currently checked out, so the reservation is stored.
3. When the book is returned via POST /lendings/return, the ReservationService triggers the ReservationObserver.
4. All registered observers (e.g., PatronReservationObserver) receive a notification.

Execution Example:
curl -X POST http://localhost:8080/reservations \
-H "Content-Type: application/json" \
-d '{"isbn":"1234567890","patronId":"P001"}'

curl -X POST http://localhost:8080/lendings/return \
-H "Content-Type: application/json" \
-d '{"isbn":"1234567890","patronId":"P002"}'

Expected Behavior:
Patron P001 receives a notification (via logs or console output) that the reserved book is now available.

---

### Strategy Pattern (Recommendations)

Concept:
The Strategy pattern allows the system to switch between different recommendation algorithms at runtime (e.g., history-based vs. genre-based).

Flow:
1. Patron requests recommendations via GET /recommendations/{patronId}.
2. Query parameter "strategy" determines which algorithm is used:
    - history → HistoryBasedRecommendation
    - genre → GenreBasedRecommendation
3. RecommendationService delegates to the chosen strategy.

Execution Example:
curl http://localhost:8080/recommendations/P001?strategy=history

curl http://localhost:8080/recommendations/P001?strategy=genre

Expected Behavior:
- For history, the system suggests books similar to those the patron has borrowed before.
- For genre, the system suggests books based on the patron’s preferred genres.

---

## 7. Notes on Patterns
- Observer Pattern: Decouples reservation notifications from lending logic. New observers can be added without changing core services.
- Strategy Pattern: Makes recommendation algorithms interchangeable and extensible. Adding a new strategy (e.g., popularity-based) requires no changes to existing code.
