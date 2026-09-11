# Jolt Coffee Shop Locator — Java Backend

This directory contains the Java and Spring Boot backend for the Jolt Coffee Shop Locator.

Jolt began as a team capstone project during my Java Full-Stack Application Development bootcamp at Tech Elevator. The project was originally built from the Tech Elevator capstone starter project, which provided the initial Spring Boot, PostgreSQL, authentication, and testing structure.

After the capstone, I independently revisited and expanded the application while continuing to develop my Java and full-stack development skills.

## Backend Technologies

- Java 11
- Spring Boot
- Spring Security
- Spring JDBC
- JdbcTemplate
- PostgreSQL
- JWT authentication
- Maven
- Yelp Fusion API

## Project Structure

The backend application is organized under:

```text
src/main/java/com/jolt/
├── controller/
├── dao/
├── exception/
├── model/
├── security/
│   └── jwt/
└── Application.java
```

The main application layers include:

- **Controllers** — Handle HTTP requests and responses.
- **DAOs** — Handle database access using `JdbcTemplate`.
- **Models** — Represent application and authentication data.
- **Security** — Handles Spring Security configuration and authentication.
- **JWT** — Creates, validates, and processes JSON Web Tokens.
- **Exceptions** — Provides application-specific database exception handling.

## Database

Inside the `database/` directory, there is an executable Bash script (`.sh` file) and several SQL scripts (`.sql` files). These can be used to build and rebuild the PostgreSQL database for the application.

From a terminal session, run:

```bash
cd database
./create.sh
```

The Bash script drops the existing database when necessary, creates the application database, and runs the SQL scripts in the required order.

Each SQL script has a specific purpose:

| File Name | Description |
| --- | --- |
| `data.sql` | Populates the database with setup or development data. |
| `dropdb.sql` | Drops the existing database and associated database users so the database can be recreated. |
| `schema.sql` | Creates the application's database objects, including tables and sequences. |
| `user.sql` | Creates the database application users and grants the required database privileges. |

### Database Users

The PostgreSQL `postgres` superuser should only be used for database administration and should not be used by the application.

The database setup creates separate users for database ownership and application access.

The application user is granted the database privileges required by the backend, including `SELECT`, `INSERT`, `UPDATE`, and `DELETE` access to application tables and appropriate sequence access.

Database connection credentials are stored in the local `application.properties` file and are not included in this repository.

## Spring Boot

The Spring Boot backend is configured to run on port `9000` during local development. This allows it to run alongside the Vue development server.

The main application entry point is:

```text
src/main/java/com/jolt/Application.java
```

### Datasource

The PostgreSQL datasource is configured through:

```text
src/main/resources/application.properties
```

The configuration includes the database connection information required by Spring Boot.

The `application.properties` file is intentionally excluded from Git because it also contains environment-specific configuration and application secrets.

### JdbcTemplate

Jolt uses Spring's `JdbcTemplate` for database access.

DAO implementations receive a `JdbcTemplate` instance through constructor injection:

```java
private final JdbcTemplate jdbcTemplate;

public JdbcUserDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}
```

The DAO layer is responsible for SQL queries, updates, database result mapping, and application-specific database error handling.

## REST Controllers

The backend exposes REST endpoints through controllers in:

```text
src/main/java/com/jolt/controller/
```

The controllers handle functionality for:

- Authentication and registration
- Coffee shop searches
- Favorites
- User profiles
- Profile images

The coffee shop search communicates with the Yelp Fusion API through the Spring Boot backend so the Yelp API key is not exposed to the Vue client.

## CORS

Controllers that are accessed by the Vue frontend allow requests from the application's configured frontend origins.

During local development, the Vue application runs separately from the Spring Boot server, so Cross-Origin Resource Sharing (CORS) configuration is required to allow the frontend and backend to communicate.

## Security

Security-related functionality is located in:

```text
src/main/java/com/jolt/security/
```

JWT-specific functionality is located in:

```text
src/main/java/com/jolt/security/jwt/
```

Jolt uses Spring Security and JSON Web Tokens (JWT) for authentication.

When a user successfully logs in, the backend creates a JWT. The Vue frontend sends that token with later authenticated requests, and the backend validates the token before establishing the user's Spring Security authentication.

The application uses stateless authentication rather than maintaining a server-side login session.

### Authentication Controller

The `AuthenticationController` provides the `/login` and `/register` routes.

Registration creates standard application users, while login authenticates the supplied credentials and returns a JWT for subsequent authenticated requests.

Passwords are stored using BCrypt hashing rather than as plaintext.

### Protected User Data

User-specific backend operations determine the current user from the authenticated Spring Security principal.

This allows operations involving profiles, profile images, and favorites to be associated with the authenticated account rather than trusting a user ID supplied by the frontend.

## External API

Coffee shop searches use the Yelp Fusion API.

The backend receives a location from the frontend and sends the search request to Yelp. Yelp API configuration is stored on the backend so credentials are not exposed in client-side code.

## Application Configuration

Local application configuration is stored in:

```text
src/main/resources/application.properties
```

This includes configuration such as:

- PostgreSQL datasource settings
- JWT settings
- Yelp API configuration

Because this file contains local configuration and secrets, it is excluded from source control.

API keys, JWT secrets, and database credentials should not be committed to the repository.

## Running the Backend

Before starting the backend, create the local database:

```bash
cd database
./create.sh
```

Then run the Spring Boot application from the Java project directory:

```bash
./mvnw spring-boot:run
```

The backend runs locally at:

```text
http://localhost:9000
```

## Testing

The original Tech Elevator capstone starter project included support for DAO integration testing using a separate test datasource and test data.

Backend tests belong under:

```text
src/test/
```

The application database schema is defined in:

```text
database/schema.sql
```

Test-specific data can be maintained separately from development data so database changes made during tests do not affect the development database.

## Project Background

The original capstone starter project provided the foundation for the database setup, Spring Boot application, authentication system, and testing structure.

During the original team capstone and my later independent development, Jolt grew beyond that starter foundation with application-specific models, database access, REST endpoints, user profiles, profile images, favorite coffee shops, Yelp-powered coffee shop searches, validation, error handling, and user-specific security.

I have intentionally maintained the project's original Java 11, Spring Boot, JdbcTemplate, and JWT-based architecture while revisiting the application. This keeps the project's development history visible while allowing me to improve its functionality, organization, security, error handling, and maintainability.