# Event Management API

Production-ready Spring Boot REST API for managing events.

## Tech Stack
- Java 17, Spring Boot 3.2.5
- PostgreSQL
- Flyway (schema versioning)
- MapStruct (DTO mapping)
- Lombok
- Bean Validation (Jakarta)

## Prerequisites
- JDK 17+
- Maven 3.8+
- PostgreSQL 15+

## Setup

1. Create database:
```sql
CREATE DATABASE event_management_db;
```

2. Update `src/main/resources/application.yml` with your DB credentials if needed.

3. Run:
```bash
mvn clean install
mvn spring-boot:run
```

Flyway will automatically create the schema on startup.

## API Endpoints

| Method | URL               | Description      |
|--------|-------------------|------------------|
| POST   | /api/events       | Create event     |
| GET    | /api/events       | Get all events   |
| GET    | /api/events/{id}  | Get event by ID  |
| PUT    | /api/events/{id}  | Update event     |
| DELETE | /api/events/{id}  | Delete event     |

## Project Structure

```
src/main/java/com/eventmanagement/
├── controller/      - REST controllers
├── service/         - Business logic
├── repository/      - Data access (JPA)
├── entity/          - JPA entities
├── dto/
│   ├── request/     - Request DTOs with validation
│   └── response/    - Response DTOs
├── mapper/          - MapStruct mappers
└── exception/       - Global exception handling
```

## Flyway Migrations

- `V1__init_schema.sql` - creates `events` table
- `V2__add_event_description.sql` - adds `description` column

## Deliverables

- Postman collection: `Event_Management_API.postman_collection.json`
- SQL migrations: `src/main/resources/db/migration/`
- Log samples: `log_samples.txt`
