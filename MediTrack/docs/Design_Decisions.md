# Design Decisions – MediTrack

## Layered Architecture
- Entity: domain objects
- Service: business logic
- Util: reusable helpers
- Factory/Strategy: extensibility

## Why Enums?
- Prevent invalid states
- Improve readability
- Safer than strings

## Why DataStore<T>?
- Demonstrates generics
- Centralized storage abstraction

## Why CSV + Serialization?
- CSV: human-readable persistence
- Serialization: object graph persistence

## Why Rule-Based AI?
- Deterministic
- No external dependencies
- Interview-defensible
