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

Exchange requests include proposed dates, participant-scoped history, accept/decline/cancel actions and completion status. Exchange events create in-app notifications; members can view their notification inbox and mark their own notifications as read. Unit tests cover notification creation and member-scoped read updates. Production-grade token/session authentication, broader API integration tests, database migrations, containerization and deployment remain outstanding.

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

To populate the local database with **10 fictional demo members** and sample offered/wanted skills, start the backend with the opt-in demo profile:

Windows:
```powershell
.\\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=demo
```

macOS/Linux:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=demo
```

The demo accounts use the username pattern `demo.<name>` (for example, `demo.alex`) and the shared local-only password `DemoPass123!`. These are fictional sample accounts, not real people. The demo seeder skips usernames that already exist and is only activated when the `demo` profile is explicitly enabled. **Never enable this profile in a public or production deployment.**

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

- [x] Exchange request, scheduling, participant-scoped history and status lifecycle
- [x] Notification inbox and read/unread state
- [x] Notifications generated for exchange requests and status changes
- [x] Opt-in local seed data for ten fictional demo members
- [x] Unit tests for notification creation and member-scoped read updates
- [ ] Production-ready authentication and session handling
- [ ] Broader service, repository, controller and frontend integration tests
- [ ] Database migrations and Docker Compose
- [ ] Deployment and demo screenshots

## Project purpose

The project demonstrates full-stack development, REST API design, relational modelling, authentication fundamentals, frontend state management and responsive UI implementation.
