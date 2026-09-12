# Jolt Coffee Shop Locator — Java Backend

### Spring Boot REST API

This directory contains the Java and Spring Boot backend for the Jolt Coffee Shop Locator.

The backend handles user authentication, PostgreSQL database access, user profiles, profile images, favorites, and coffee shop searches through the Yelp Fusion API.

It uses a straightforward layered structure that keeps the different parts of the application separated while remaining easy to follow.

---

## Backend Technologies

- Java 11
- Spring Boot
- Spring Security
- Spring JDBC
- JdbcTemplate
- PostgreSQL
- JWT Authentication
- BCrypt
- Maven
- Yelp Fusion API
- JUnit
- Spring Boot Test
- Spring Security Test

---

## Project Structure

The main backend code is located under:

```text
src/main/java/com/jolt/
```

```text
com/jolt/

├── config/
│   └── AppConfig.java
├── controller/
│   ├── AuthenticationController.java
│   ├── CoffeeController.java
│   ├── FavoritesController.java
│   ├── ImageController.java
│   └── ProfileController.java
├── dao/
│   ├── FavoritesDao.java
│   ├── ImageDao.java
│   ├── JdbcFavoritesDao.java
│   ├── JdbcImageDao.java
│   ├── JdbcProfileDao.java
│   ├── JdbcUserDao.java
│   ├── ProfileDao.java
│   └── UserDao.java
├── dto/
│   ├── LoginDto.java
│   ├── LoginResponseDto.java
│   └── RegisterUserDto.java
├── exception/
│   └── DaoException.java
├── model/
│   ├── Authority.java
│   ├── Favorites.java
│   ├── Image.java
│   ├── Profile.java
│   └── User.java
├── security/
│   ├── jwt/
│   │   ├── JWTConfigurer.java
│   │   ├── JWTFilter.java
│   │   └── TokenProvider.java
│   ├── JwtAccessDeniedHandler.java
│   ├── JwtAuthenticationEntryPoint.java
│   ├── SecurityUtils.java
│   ├── UserModelDetailsService.java
│   ├── UserNotActivatedException.java
│   └── WebSecurityConfig.java
└── JoltApplication.java
```

### Main Application Layers

- **Configuration** — Contains shared Spring configuration and application beans.
- **Controllers** — Handle incoming HTTP requests and responses.
- **DAOs** — Handle PostgreSQL database operations using JdbcTemplate.
- **DTOs** — Transfer authentication and registration data between the client and server.
- **Models** — Represent application and authentication data.
- **Security** — Handles authentication and protected application access.
- **JWT** — Creates and validates authentication tokens.
- **Exceptions** — Handles application-specific database errors.

---

## Database

Database scripts are located in:

```text
database/
```

```text
database/

├── create.sh
├── data.sql
├── dropdb.sql
├── schema.sql
└── user.sql
```

### Database Scripts

| File | Purpose |
| --- | --- |
| `create.sh` | Coordinates the local database setup process. |
| `data.sql` | Adds development and demonstration data. |
| `dropdb.sql` | Removes the existing Jolt database while leaving the database roles available. |
| `schema.sql` | Creates the application tables, relationships, and constraints. |
| `user.sql` | Creates the Jolt database roles if they do not already exist. |

The setup script rebuilds the local Jolt database and loads the required schema and development data.

---

## Database Structure

The main Jolt database tables are:

```text
users
favorites
profile
image
```

User-owned data is connected to the `users` table using foreign keys.

The `profile` and `image` tables allow one record per user, while each user can save multiple favorites.

The `favorites` table also contains a unique constraint that prevents the same Yelp business from being saved more than once by the same user.

Dependent profile, image, and favorite records use database relationships to keep user-owned data connected to the correct account.

---

## Database Roles

Jolt separates database ownership from normal application access.

```text
postgres
    ↓
Database administration

jolt_owner
    ↓
Owns Jolt database objects

jolt_appuser
    ↓
Spring Boot application access
```

### `postgres`

The PostgreSQL administrator account is used for database administration and setup.

The application does not connect using this account.

### `jolt_owner`

`jolt_owner` owns the Jolt database objects and does not allow normal application login.

### `jolt_appuser`

The Spring Boot application connects to PostgreSQL using `jolt_appuser`.

This account receives the database permissions required by the application, including:

- `SELECT`
- `INSERT`
- `UPDATE`
- `DELETE`
- Required sequence access

This keeps the application from running with PostgreSQL administrator privileges.

---

## Authentication

Authentication is handled through Spring Security and JSON Web Tokens.

The main authentication endpoints are:

```text
POST /login
POST /register
```

When a user registers:

1. The backend validates the registration request.
2. The password is hashed using BCrypt.
3. The account is stored in PostgreSQL with the standard user role.

When a user logs in:

1. Spring Security authenticates the username and password.
2. The backend creates a JWT.
3. The token is returned to the Vue frontend.
4. Future protected requests include the token in the `Authorization` header.

Jolt uses stateless authentication, so the backend does not maintain a server-side login session.

---

## Protected User Data

Favorites, profiles, and profile images belong to individual authenticated users.

For protected operations, the backend gets the current username from the authenticated Spring Security principal and then determines the corresponding database user.

This means the frontend does not decide which user ID should be used for protected data.

This approach is used for:

- Favorites
- Profiles
- Profile images

It helps prevent one user from retrieving or changing another user's protected application data.

---

## Coffee Shop Search

Coffee shop searches are handled through the Yelp Fusion API.

The frontend sends a location to the Jolt backend.

The backend then:

1. Builds the Yelp business search request.
2. Adds the private Yelp API key.
3. Sends the request to Yelp.
4. Returns the business results to the Vue frontend.

Keeping the Yelp request on the backend prevents the private API key from being included in frontend JavaScript.

---

## Favorites

Authenticated users can save coffee shops returned through Yelp searches.

Saved favorites include information such as:

- Yelp business ID
- Business name
- Address
- Business image
- Yelp URL

Favorites are connected to the authenticated user's account.

Before creating a favorite, the application checks whether the business has already been saved.

The database also contains a unique constraint that provides an additional level of duplicate prevention.

Users can only retrieve and delete favorites associated with their own account.

---

## User Profiles

Each authenticated user can create one profile.

Profile information includes:

- First name
- Last name
- Birth date
- Address
- City
- State
- ZIP code
- Form completion status

The profile workflow tracks whether the user has already submitted their initial profile.

The birth date is collected during the initial profile creation and is not changed through later profile updates.

---

## Profile Images

Authenticated users can upload a profile picture.

The application supports:

- JPEG
- PNG
- WebP

Uploaded image data is stored in PostgreSQL using the `BYTEA` data type.

Each user can have one profile image.

Uploading another image replaces the current image, and users can also remove their existing profile picture.

The backend validates uploaded file types before saving the image.

---

## CORS

During local development, the Vue frontend and Spring Boot backend run on different ports.

The backend allows requests from the Jolt frontend origins:

```text
http://localhost:5173
https://jolt.jennifercurtis.me
```

This allows the local Vue development server and deployed Jolt frontend to communicate with the backend while avoiding unrestricted cross-origin access.

---

## Application Configuration

Spring Boot configuration is located in:

```text
src/main/resources/application.properties
```

The configuration includes:

- Backend server port
- PostgreSQL datasource configuration
- JWT settings
- File upload limits
- Yelp Fusion API configuration

Sensitive values are supplied through environment variables instead of being written directly into source code.

The application uses:

```text
DB_PASSWORD
JWT_BASE64_SECRET
YELP_API_KEY
```

Database setup and backend tests also use:

```text
DB_ADMIN_PASSWORD
```

The actual values should remain in the local root `.env` file and should never be committed to GitHub.

---

## Local Environment

The repository contains:

```text
.env.example
load-env.sh
```

The example environment file documents the required private values without containing actual credentials.

Before running the backend from Git Bash, load the local environment from the project root:

```bash
source load-env.sh
```

A successful load displays:

```text
Jolt environment loaded.
```

---

## Creating the Local Database

PostgreSQL must be running before creating the Jolt database.

From the Java project directory:

```bash
cd database
./create.sh
```

The setup process:

1. Removes the existing local `jolt` database.
2. Creates the required Jolt database roles if needed.
3. Creates a new `jolt` database owned by `jolt_owner`.
4. Creates the database schema.
5. Loads development data.
6. Grants the required application permissions to `jolt_appuser`.

After the database has been created, return to the Java directory:

```bash
cd ..
```

---

## Running the Backend

From the Java project directory:

```bash
./mvnw spring-boot:run
```

The backend runs locally at:

```text
http://localhost:9000
```

---

## Testing

Automated backend tests are located under:

```text
src/test/
```

The test suite covers the main database, controller, authentication, and security behavior of Jolt.

### DAO Tests

DAO integration tests cover:

- Users
- Favorites
- Profiles
- Profile images

These tests run against a separate PostgreSQL test database:

```text
jolt_test
```

This allows the actual SQL and PostgreSQL behavior to be tested without modifying the normal development database.

Database changes made during individual DAO tests are rolled back after each test.

### Controller Tests

Controller tests cover:

- Authentication
- Coffee shop searches
- Favorites
- Profiles
- Profile images

These tests verify request handling, response behavior, authenticated-user workflows, and application-specific errors.

### Security Tests

Security testing covers:

- JWT token creation and validation
- JWT request filtering
- User details loading
- Security utilities
- Authentication entry-point behavior
- Access-denied behavior
- Protected endpoint access

The security integration tests also verify that protected endpoints reject unauthenticated requests.

### Running the Tests

After loading the required environment variables, run:

```bash
./mvnw test
```

---

## Backend Design

Jolt intentionally uses a straightforward Java and Spring Boot structure.

The project separates:

```text
HTTP requests
    ↓
Controllers
    ↓
DAO interfaces and JDBC implementations
    ↓
PostgreSQL
```

Authentication and security are handled separately through Spring Security and JWT classes.

This structure keeps the backend understandable while still separating the main responsibilities of the application.

Jolt is one of my earlier full-stack projects, so I have intentionally kept the backend readable rather than adding additional layers or abstractions that the application does not currently need.

---

## Related Documentation

For the complete project overview, screenshots, and project history, see:

```text
../README.md
```

Frontend-specific information is available in:

```text
../vue/README.md
```
