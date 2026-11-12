# SkillSwap

SkillSwap is a full-stack learning-exchange application. Members can list skills they can teach, list skills they want to learn, and discover other members with complementary interests.

**Status:** Active development. The full-stack foundation is on the `feature/full-stack-skillswap` branch. It is not yet a production service.

## Technology

**Backend:** Java 24, Spring Boot 3.5.5, Spring Web, Spring Data JPA, Spring Security, Bean Validation, MySQL and Maven.

**Frontend:** React 19, TypeScript, Vite and Lucide icons.

## Implemented in this development branch

- Account registration with username/email uniqueness checks
- BCrypt password hashing
- Authenticated API access using HTTP Basic for local development
- Current-user profile endpoint
- Personal offered/wanted skill listings
- Server-side ownership checks for skill deletion
- Match discovery based on complementary skills
- Responsive React dashboard, registration and sign-in screens

Exchange scheduling, notifications, production-grade token/session authentication, automated integration tests, database migrations, containerization and deployment remain to be completed.

## Repository structure

```text
SkillSwap/
├── frontend/                  # React + TypeScript client
├── src/main/java/             # Spring Boot API
├── src/main/resources/        # Application configuration
├── src/test/                  # Backend tests
├── pom.xml
└── README.md
```

## Requirements

- Java 24
- Node.js 20+ and npm
- MySQL 8

## Run locally

### 1. Create the database

Create a MySQL database named `skillswap`. The backend defaults to localhost:3306 and the local MySQL root account. Override credentials with environment variables; never commit real credentials.

PowerShell:
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/skillswap"
$env:DB_USERNAME="your_mysql_username"
$env:DB_PASSWORD="your_mysql_password"
```

### 2. Start the backend

From the repository root:

Windows:
```powershell
.\mvnw.cmd spring-boot:run
```

macOS/Linux:
```bash
./mvnw spring-boot:run
```

API base URL: `http://localhost:8080`.

### 3. Start the frontend

In a second terminal:
```bash
cd frontend
npm install
npm run dev
```

Open `http://localhost:5173`. Set `VITE_API_URL` if the API runs on another host.

## Build and test

Backend: `./mvnw clean verify` (Windows: `mvnw.cmd clean verify`).

Frontend:
```bash
cd frontend
npm run build
```

## Security notes

Passwords are BCrypt-hashed before persistence. The current development branch uses HTTP Basic authentication and is intended for local development. Do not expose it as a public service without HTTPS, a production-ready authentication design, rate limiting, hardened CORS, database migrations, and a security review. API endpoints require authentication except registration.

## Roadmap

- [ ] Exchange request, acceptance, scheduling and status lifecycle
- [ ] Notification inbox and read/unread state
- [ ] Production-ready authentication and session handling
- [ ] More service, repository, controller and frontend tests
- [ ] Database migrations and Docker Compose
- [ ] Deployment and demo screenshots

## Project purpose

The project demonstrates full-stack development, REST API design, relational modelling, authentication fundamentals, frontend state management and responsive UI implementation.
