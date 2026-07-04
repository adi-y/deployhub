# DeployHub

**A Vercel-inspired deployment platform, built from scratch with Spring Boot.**

DeployHub recreates the core engine behind platforms like Vercel, Netlify, and Railway — authentication, project ownership, Git integration, build automation, and Docker-based deployment — to understand how a one-click "Deploy" button actually works under the hood.

![Java](https://img.shields.io/badge/Java-24-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![Status](https://img.shields.io/badge/status-in%20development-yellow)

---

## Why I'm building this

Most developers use deployment platforms without ever seeing what happens between `git push` and a live URL. Rather than consume that abstraction, DeployHub builds it — layer by layer: secure auth, project ownership, repo cloning, a build engine, containerization, and eventually live deployment URLs behind a reverse proxy.

## Current Status — `v0.2`

| Phase | Scope | Status |
|---|---|---|
| 1 | User authentication, JWT security, exception handling | ✅ Done |
| 2 | Project management, ownership, entity mapping | ✅ Done |
| 3 | Git repository cloning | 🚧 In progress |
| 4 | Build engine (`npm install` / `npm run build`, logs) | ⏳ Planned |
| 5 | Docker image build & container management | ⏳ Planned |
| 6 | Live deployment URLs, reverse proxy, dashboard | ⏳ Planned |

## Features

**Authentication & Security**
- JWT-based stateless authentication with a custom `JwtFilter`
- BCrypt password hashing, custom `UserDetailsService`
- Handles expired/invalid/missing tokens explicitly rather than failing silently
- Every protected endpoint resolves the current user from the `SecurityContext` — never from a client-supplied ID

**Project Management**
- Authenticated users create projects and attach a GitHub repo URL
- Ownership is enforced server-side via `CurrentUserService`, so users only ever see their own projects

**Reliability**
- Centralized exception handling (`EmailAlreadyExistsException`, `InvalidCredentialsException`, `UserNotFoundException`, etc.) for consistent, predictable API responses

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 24 |
| Framework | Spring Boot 4 |
| Security | Spring Security, JWT (JJWT) |
| Persistence | MySQL, Spring Data JPA / Hibernate |
| Tooling | Maven, Lombok |
| Upcoming | Docker, ProcessBuilder-based build engine |

## Architecture

**Request flow**
```
Client → Controller → Service → CurrentUserService → Repository → MySQL
```

**Auth flow**
```
Login → Validate Credentials → Generate JWT → Return Token
Request → JwtFilter → Validate Token → SecurityContext → Controller
```

## Project Structure

```
src/main/java
├── controller     # REST endpoints
├── dto
│   ├── request
│   └── response
├── entity         # JPA entities
├── exception       # Custom exceptions + global handler
├── mapper          # Entity ↔ DTO mapping
├── repository      # Spring Data JPA repositories
├── security        # JwtFilter, JWT utils, UserDetailsService
├── service
│   └── impl
└── config
```

## API Reference

**Auth**

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate and receive a JWT |

**Projects** *(requires `Authorization: Bearer <token>`)*

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/projects` | Create a new project |
| GET | `/api/projects` | List the current user's projects |

## Getting Started

**Prerequisites:** Java 24, Maven 3.9+, MySQL 8.x

```bash
git clone https://github.com/<your-username>/deployhub.git
cd deployhub
```

Configure `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/deployhub
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your-256-bit-secret
jwt.expiration=86400000
```

Run it:
```bash
mvn clean install
mvn spring-boot:run
```

App starts on `http://localhost:8080`.

## What's Next

- Deployment workers with a queue (RabbitMQ)
- Redis caching for build/deployment status
- Custom domains + HTTPS certificates
- CI/CD pipeline for DeployHub itself

## License

Built for learning and portfolio purposes.