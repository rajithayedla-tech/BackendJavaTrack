## Why ArrayList instead of Array?
- **Dynamic size:** Unlike arrays, `ArrayList` can grow/shrink as students, courses, and enrollments are added or removed.
- **Convenient methods:** Provides built-in methods like `add()`, `remove()`, `stream()`, and `contains()` which simplify logic.
- **Cleaner code:** Avoids manual index management and resizing, keeping methods short and readable.

---

## Where static members were used and why?
- **IdGenerator utility:**
  - Static counters (`studentIdCounter`, `courseIdCounter`, `enrollmentIdCounter`) ensure unique IDs across the application.
  - Static methods (`getNextStudentId()`, etc.) provide a single source of truth for ID generation.
- **Reason:** IDs must be globally consistent; static members avoid duplication and keep logic centralized.

---

## Where inheritance was used and what was gained?
- **Person → Student / Trainer**
  - Shared fields (`id`, `firstName`, `lastName`, `email`) moved to `Person`.
  - `Student` and `Trainer` extend `Person` and override `getDisplayName()` for specialized behavior.
- **Benefits:**
  - **Code reuse:** Avoids repeating common fields and methods.
  - **Polymorphism:** Allows treating `Student` and `Trainer` as `Person` when needed.
  - **Extensibility:** Easy to add more roles (e.g., `Admin`) without duplicating base logic.

---

## Clean Code Practices
- **Meaningful names:** Methods like `addStudent`, `findById`, `deactivateStudent` clearly describe their purpose.
- **Short methods:** Each method performs a single responsibility (e.g., `addStudentFlow()` only handles adding).
- **Encapsulation:** Private fields with public getters/setters prevent direct manipulation.
- **Consistency:** Package structure separates concerns (entities, services, UI, exceptions, utils).