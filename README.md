# SkillSwap

SkillSwap is a full-stack learning-exchange application. Members can list skills they can teach, list skills they want to learn, discover people with complementary interests, arrange exchanges and receive notifications.

**Status:** Full-stack MVP foundation merged into `main`. Local development is supported; the application is not yet production-ready or publicly deployed.

## Technology

- **Backend:** Java 24, Spring Boot 3.5.5, Spring Web, Spring Data JPA, Spring Security, Bean Validation and Maven
- **Databases:** MySQL 8 for persistent local development; H2 in-memory database for the opt-in demo profile
- **Frontend:** React 19, TypeScript, Vite and Lucide icons

## Implemented features

- Account registration with username/email uniqueness checks and BCrypt password hashing
- Authenticated API access using HTTP Basic for local development
- Current-user profile and personal offered/wanted skill listings
- Server-side ownership checks for skill deletion
- Match discovery based on complementary skills
- Responsive dashboard, registration and sign-in screens
- Exchange requests, proposed dates, participant-scoped history, accept/decline/cancel actions and completion status
- In-app notifications for exchange events, including an inbox and read/unread state
- CI checks for backend Maven verification and frontend production build

## Requirements

- Java 24
- Node.js 20+ and npm

MySQL 8 is required for the persistent MySQL profile. It is **not** required for the local demo profile.

## Run locally with demo data (recommended first run)

The demo profile uses an isolated in-memory H2 database and seeds ten fictional sample members. It does not require a MySQL server.

From the repository root, start the backend in PowerShell:

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=demo"
```

Or from Command Prompt:

```cmd
mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=demo"
```

Wait until the log contains both `Tomcat started on port 8080` and `Started SkillSwapApplication`.

In a **second** terminal, start the frontend:

```powershell
cd frontend
npm install
npm run dev
```

Open [http://localhost:5173](http://localhost:5173).

Demo login:

- **Username:** `demo.alex`
- **Password:** `DemoPass123!`

Other fictional demo accounts use the same local-only password. Demo data lives only in memory and is reset when the backend stops. The demo profile is for local evaluation only. **Never enable it in a public or production deployment.**

## Run with persistent MySQL

1. Start MySQL 8 and create a database named `skillswap`.
2. Set your database connection environment variables. PowerShell example:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/skillswap"
$env:DB_USERNAME="your_mysql_username"
$env:DB_PASSWORD="your_mysql_password"
```

3. Start the backend without the demo profile:

```powershell
.\mvnw.cmd spring-boot:run
```

The application defaults to `jdbc:mysql://localhost:3306/skillswap`, username `root`, and an empty password when the environment variables are not supplied. Configure credentials appropriate to your local MySQL installation. Never commit real credentials.

## Build and test

Backend:

```powershell
.\mvnw.cmd clean verify
```

Frontend:

```powershell
cd frontend
npm install
npm run build
```

## Configuration

- Backend API: `http://localhost:8080`
- Frontend dev server: `http://localhost:5173`
- Optional frontend API override: set `VITE_API_URL` before running Vite, for example `VITE_API_URL=http://localhost:8080` (use the appropriate environment syntax for your shell).

## Security and production limitations

The current development authentication uses HTTP Basic and is intended for local development. Do not expose this configuration as a public service without HTTPS, a production-ready authentication/session design, rate limiting, hardened CORS, database migrations, secret management, integration testing and a security review. API endpoints require authentication except registration. The demo profile and shared demo password must never be used in a public deployment.

## Roadmap

- [x] Exchange request, scheduling, participant-scoped history and status lifecycle
- [x] Notification inbox and read/unread state
- [x] Notifications generated for exchange requests and status changes
- [x] Opt-in local seed data for ten fictional demo members
- [x] Backend and frontend CI build checks
- [ ] Production-ready authentication and session handling
- [ ] Broader service, repository, controller and frontend integration tests
- [ ] Database migrations and Docker Compose
- [ ] Deployment and demo screenshots

## Project purpose

The project demonstrates full-stack development, REST API design, relational modelling, authentication fundamentals, frontend state management and responsive UI implementation.
