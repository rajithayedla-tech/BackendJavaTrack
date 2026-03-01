# 🚗 Parking Lot System – Spring Boot Capstone Project

Parking Lot System is a backend application developed to demonstrate end-to-end proficiency in **Spring Boot**, **JPA/Hibernate**, and **RESTful APIs**. It simulates a smart parking lot with vehicle check-in/check-out, spot allocation, and transaction tracking.

---

## 📌 Features

- Vehicle, Parking Spot, and Transaction management (CRUD)
- Enum-based vehicle types (CAR, BUS, MOTORCYCLE)
- REST API endpoints for check-in, check-out, and availability
- Fee calculation based on parking duration
- H2 in-memory database with console access
- Repository abstraction with Spring Data JPA
- Configurable database (H2 for dev, MySQL for prod)
- Unit & integration testing with JUnit 5

---

## 🛠 Prerequisites

- Java JDK 17 or higher
- Gradle (or use included Gradle wrapper)
- Git (optional, for version control)

Verify installation:
```bash
java -version
javac -version
gradle -v
```

## 🏗 Design Decisions

The Parking Lot System was built with clarity, modularity, and scalability in mind. Key design choices include:

### 1. Layered Architecture
- **Entity Layer**: Defines core domain models (`Vehicle`, `ParkingSpot`, `ParkingTransaction`) with JPA annotations.
- **Repository Layer**: Uses Spring Data JPA interfaces for CRUD and query derivation (`findByVehicle_Id`, `findBySpot_Id`).
- **Service Layer**: Encapsulates business logic such as spot allocation, fee calculation, and transaction lifecycle.
- **Controller Layer**: Exposes REST endpoints for check-in, check-out, and availability queries.

### 2. Database Strategy
- **H2 In-Memory Database**: Chosen for development and testing due to zero setup and fast startup.
- **MySQL Option**: Supported for production deployments by switching `application.properties`.

### 3. Enum Usage
- **VehicleType Enum**: Ensures type safety for vehicle categories (CAR, BUS, MOTORCYCLE).
- **Spot Size Enum**: Matches vehicle type to parking spot size

### 📊 Class Diagram

```mermaid
classDiagram
    class Vehicle {
        +Long id
        +String licensePlate
        +VehicleType type
        +LocalDateTime entryTime
        +LocalDateTime exitTime
    }

    class ParkingSpot {
        +Long id
        +int floor
        +int spotNumber
        +SpotSize size
        +boolean isOccupied
    }

    class ParkingTransaction {
        +Long id
        +LocalDateTime entryTime
        +LocalDateTime exitTime
        +double fee
    }

    class VehicleType {
        <<enumeration>>
        CAR
        BUS
        MOTORCYCLE
    }

    class SpotSize {
        <<enumeration>>
        CAR
        BUS
        MOTORCYCLE
    }

    Vehicle "1" --> "0..*" ParkingTransaction : involved in
    ParkingSpot "1" --> "0..*" ParkingTransaction : allocated to
    ParkingTransaction "1" --> "1" Vehicle : references
    ParkingTransaction "1" --> "1" ParkingSpot : references
