# SkillSwap

SkillSwap is a Java backend project for a platform concept where people exchange skills instead of paying money. The repository uses Spring Boot and Spring Data JPA to establish the application backend and database layer.

> **Project status:** Development project. Check the source code and tests for the functionality currently implemented; the project description is the intended product concept, not a claim that every planned feature is complete.

## Technology

- **Language:** Java 24
- **Backend framework:** Spring Boot 3.5.5
- **API layer:** Spring Web
- **Persistence:** Spring Data JPA
- **Database driver:** MySQL Connector/J
- **Developer tools:** Maven Wrapper, Lombok, Spring Boot DevTools
- **Testing:** Spring Boot Starter Test

## Intended concept

The application is intended to support a skills-exchange experience in which participants can offer skills and learn from other participants without a cash payment.

Possible domain areas include user profiles, skill listings and exchange coordination. Refer to the current source code for implemented behaviour.

## Project structure

- `src/main/java/` — Java application source
- `src/main/resources/` — application configuration and resources
- `src/test/` — automated tests
- `pom.xml` — Maven dependencies and build configuration
- `mvnw`, `mvnw.cmd` — Maven Wrapper scripts

## Requirements

- Java 24
- MySQL
- Git

## Getting started

1. Clone the repository.
2. Create a MySQL database for the application.
3. Configure the database connection in your local application configuration or environment variables.
4. Do not commit database passwords or other secrets.
5. Run the application using the Maven Wrapper:

**Windows**
```powershell
.\mvnw.cmd spring-boot:run
```

**macOS / Linux**
```bash
./mvnw spring-boot:run
```

## Run tests

**Windows**
```powershell
.\mvnw.cmd test
```

**macOS / Linux**
```bash
./mvnw test
```

## Build

```bash
./mvnw clean package
```

On Windows, use `mvnw.cmd` instead of `./mvnw`.

## Engineering concepts

- Java and object-oriented application development
- REST API development with Spring Web
- Relational persistence with JPA
- MySQL integration
- Dependency management and repeatable builds with Maven
- Automated testing

## Current limitations

This README documents the repository's framework and intended purpose. Build success, endpoint coverage, database migrations and end-to-end behaviour should be verified against the current source before describing the project as production-ready.
