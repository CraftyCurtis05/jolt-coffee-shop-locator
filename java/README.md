# Jolt Coffee Shop Locator — Java Backend

This directory contains the Java and Spring Boot backend for the Jolt Coffee Shop Locator.

The Jolt backend provides authentication, user-specific data management, PostgreSQL persistence, profile image storage, favorites, and coffee shop search integration through the Yelp Fusion API.

The application uses a layered Spring Boot architecture with REST controllers, DAO-based database access, JWT authentication, and PostgreSQL.

## Backend Technologies

- Java 11
- Spring Boot
- Spring Security
- Spring JDBC
- JdbcTemplate
- PostgreSQL
- JWT Authentication
- Maven
- Yelp Fusion API

## Project Structure

The backend application is organized under:

```text
src/main/java/com/jolt/

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

The main application layers include:

- **Configuration** — Provides application-level Spring configuration and shared beans.
- **Controllers** — Handle HTTP requests and responses.
- **DAOs** — Handle PostgreSQL database access using `JdbcTemplate`.
- **DTOs** — Represent authentication request and response data.
- **Models** — Represent application and authentication data.
- **Security** — Handles Spring Security configuration and authenticated-user behavior.
- **JWT** — Creates, validates, and processes JSON Web Tokens.
- **Exceptions** — Provides application-specific database exception handling.

## Database

Database setup scripts are located in:

```text
database/
```

The directory contains:

```text
database/

├── create.sh
├── data.sql
├── dropdb.sql
├── schema.sql
└── user.sql
```

From the Java project directory, run:

```bash
cd database
./create.sh
```

The setup process rebuilds the local PostgreSQL database and executes the required SQL scripts in order.

| File Name | Description |
| --- | --- |
| `create.sh` | Coordinates the local database setup process. |
| `data.sql` | Adds development and demonstration data. |
| `dropdb.sql` | Removes the existing Jolt database and associated database users. |
| `schema.sql` | Creates the Jolt database tables, relationships, and constraints. |
| `user.sql` | Creates the database users and grants the required privileges. |

### Database Structure

The Jolt database contains the following primary tables:

```text
users
favorites
profile
image
```

User-owned data is connected to the `users` table through foreign keys.

The `profile` and `image` tables allow one record per user, while `favorites` allows multiple records per user.

Favorites include a database-level unique constraint preventing the same Yelp business from being saved more than once for the same account.

Dependent profile, image, and favorite records are removed automatically when their associated user is deleted.

### Database Users

Jolt uses separate PostgreSQL users for database ownership and application access:

```text
jolt_owner
jolt_appuser
```

The PostgreSQL `postgres` superuser is used for database administration and should not be used by the application.

The Jolt application connects using `jolt_appuser`, which receives the database privileges required by the backend, including:

- `SELECT`
- `INSERT`
- `UPDATE`
- `DELETE`
- Required sequence access

Database passwords are supplied through local environment configuration and should not be committed to the repository.

## Spring Boot

The Spring Boot backend runs on port `9000` during local development.

The main application entry point is:

```text
src/main/java/com/jolt/JoltApplication.java
```

The application can be started from the Java project directory using:

```bash
./mvnw spring-boot:run
```

The local backend is available at:

```text
http://localhost:9000
```

## Application Configuration

Local Spring Boot configuration is stored in:

```text
src/main/resources/application.properties
```

The configuration includes:

- PostgreSQL datasource settings
- JWT authentication settings
- File upload limits
- Yelp Fusion API configuration

Sensitive values are supplied through environment variables rather than being stored directly in source code.

The backend expects environment configuration for:

```text
DB_PASSWORD
JWT_BASE64_SECRET
YELP_API_KEY
```

The local `application.properties` file is excluded from source control because it contains environment-specific application configuration.

API keys, JWT signing secrets, and database credentials should never be committed to the repository.

## JdbcTemplate and DAO Layer

Jolt uses Spring's `JdbcTemplate` for database access.

DAO implementations receive a `JdbcTemplate` instance through constructor injection:

```java
private final JdbcTemplate jdbcTemplate;

public JdbcUserDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}
```

The DAO layer is responsible for:

- SQL queries
- Inserts, updates, and deletes
- Database result mapping
- User-specific data access
- Application-specific database error handling

DAO interfaces define the database operations available to the application, while JDBC implementations contain the PostgreSQL-specific queries.

## REST Controllers

REST controllers are located in:

```text
src/main/java/com/jolt/controller/
```

The backend contains controllers for:

- Authentication and registration
- Coffee shop searches
- Favorites
- User profiles
- Profile images

The controllers connect HTTP requests to the application's database, security, and external API functionality.

## Authentication

Authentication and registration are handled by:

```text
AuthenticationController.java
```

The authentication controller provides:

```text
/login
/register
```

Registration creates standard Jolt user accounts.

Passwords are hashed with BCrypt before being stored in the database.

When a user successfully logs in, the backend creates a JSON Web Token that is returned to the frontend for later authenticated requests.

## Security

Security functionality is located in:

```text
src/main/java/com/jolt/security/
```

JWT-specific functionality is located in:

```text
src/main/java/com/jolt/security/jwt/
```

Jolt uses Spring Security with stateless JWT authentication.

The security layer includes:

- Spring Security configuration
- JWT request filtering
- Token creation and validation
- Authentication entry-point handling
- Access-denied handling
- Authenticated-user lookup

The backend does not maintain a server-side login session.

Instead, authenticated requests include a JWT that is validated before the user's Spring Security authentication is established.

## Protected User Data

User-specific operations determine the current account from the authenticated Spring Security principal.

This is used for:

- Favorites
- Profiles
- Profile images

The backend determines the authenticated user's database ID rather than trusting a user ID supplied by the frontend.

This helps prevent one user from accessing or modifying another user's protected data.

## Favorites

Authenticated users can save coffee shops returned by Yelp.

Favorite records include information such as:

- Yelp business ID
- Business name
- Address
- Image URL
- Yelp URL

Favorites are associated with the authenticated user's account.

The application checks for existing favorites, and the database also includes a unique constraint preventing the same Yelp business from being saved more than once for the same user.

## User Profiles

Users can create and update profile information associated with their account.

Profile data includes:

- First and last name
- Birth date
- Address
- City
- State
- ZIP code
- Form-completion status

Each user can have one profile record.

## Profile Images

Users can upload and manage a profile image associated with their account.

Image data is stored in PostgreSQL using the `BYTEA` data type.

Each user can have one stored profile image.

The backend validates uploaded image data and returns stored images to the frontend as encoded image data.

## Yelp Fusion API

Coffee shop searches use the Yelp Fusion API.

The frontend sends a search location to the Jolt backend. The backend sends the search request to Yelp and returns the business data to the Vue application.

Keeping the Yelp request on the backend prevents the Yelp API key from being exposed in client-side code.

## CORS

The backend allows requests from the configured Jolt frontend origins.

During local development, the Vue frontend and Spring Boot backend run separately, so Cross-Origin Resource Sharing (CORS) is required for communication between the two applications.

The backend supports the local Vue development origin as well as the deployed Jolt frontend origin.

## File Uploads

Spring Boot multipart configuration controls profile image upload limits.

The application currently supports a maximum file size and multipart request size of:

```text
10 MB
```

## Testing

Backend integration tests are located under:

```text
src/test/
```

The current test structure includes:

```text
src/test/

├── java/
│   └── com/
│       └── jolt/
│           ├── config/
│           │   └── TestingDatabaseConfig.java
│           └── dao/
│               ├── BaseDaoTests.java
│               └── JdbcUserDaoTests.java
└── resources/
    └── test-data.sql
```

### DAO Integration Testing

DAO tests run against a separate PostgreSQL test database:

```text
jolt_test
```

The testing configuration:

1. Creates or connects to the Jolt test database.
2. Runs `database/schema.sql`.
3. Loads `src/test/resources/test-data.sql`.
4. Provides a Spring `DataSource` to the DAO tests.
5. Rolls back database changes after each test.

This allows DAO behavior and SQL queries to be tested against PostgreSQL without modifying the development database.

## Running the Backend

Before starting the server, ensure PostgreSQL is running and the required environment variables are configured.

### 1. Create the Database

From the Java project directory:

```bash
cd database
./create.sh
```

### 2. Return to the Java Project Directory

```bash
cd ..
```

### 3. Start Jolt Server

```bash
./mvnw spring-boot:run
```

The backend runs locally at:

```text
http://localhost:9000
```

## Jolt Backend Architecture

The Jolt backend intentionally retains its Java 11, Spring Boot, JdbcTemplate, PostgreSQL, and JWT-based architecture.

The project uses a straightforward layered structure that separates HTTP handling, database access, authentication, application data, configuration, and external API integration.

This structure keeps the backend maintainable and understandable while preserving the application's development history.