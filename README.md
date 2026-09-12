# Jolt Coffee Shop Locator

### Full-Stack Coffee Shop Discovery Application

Jolt is a full-stack web application that helps users discover local coffee shops, view real-time business information, save favorite locations, and manage a personalized user profile.

Originally developed as a collaborative capstone project during the Tech Elevator Full-Stack Java Bootcamp, Jolt was later independently redesigned and expanded. I continued working on the application after the bootcamp to improve its organization, user experience, security, testing, and overall functionality.

The version in this repository reflects the continued development completed after the original capstone project.

---

## Project Evolution

Jolt began as a collaborative capstone project focused on helping users find nearby coffee shops using real-time business data from the Yelp Fusion API.

After completing the bootcamp, I continued developing the application independently. I redesigned the user interface, reorganized parts of the application, expanded user workflows, improved database and authentication security, added automated testing, and continued improving the overall user experience.

This project represents one of my first full-stack applications and has also given me the opportunity to continue applying what I have learned as my development skills have grown.

---

## Project Overview

Jolt brings coffee shop discovery and personalized user features together in one application.

Users can:

- Register and log in to a secure account
- Search for nearby coffee shops
- View real-time business information from the Yelp Fusion API
- Save coffee shops to a personalized favorites list
- Create and update a user profile
- Upload and manage a profile picture

The application uses a Vue.js frontend, a Java and Spring Boot REST API, and a PostgreSQL database.

---

## Key Features

### Coffee Shop Discovery

- Search for coffee shops by location
- Retrieve current business information through the Yelp Fusion API
- View business names, addresses, images, and additional Yelp information
- Browse search results through a centralized interface

### User Authentication

- User registration and login
- BCrypt password hashing
- JWT-based authentication
- Protected user-specific application features
- Authenticated requests between the Vue frontend and Spring Boot backend

### Favorites

- Save coffee shops returned through Yelp searches
- View saved locations from a personalized favorites list
- Remove saved favorites
- Prevent duplicate favorites for the same user
- Restrict favorite management to the authenticated account

### User Profiles

- Create a personalized profile
- Update existing profile information
- Maintain profile information separately for each authenticated user
- Track profile form completion
- Protect profile data using the authenticated user's account

### Profile Images

- Upload a profile picture
- Replace an existing profile picture
- Remove a profile picture
- Validate supported image types
- Store profile image data in PostgreSQL

---

## Independent Development

Following the original capstone project, I continued developing Jolt independently.

Some of the changes and additions include:

- Redesigned the application's user interface
- Reorganized the project and removed duplicate backend files
- Expanded profile management functionality
- Improved favorites functionality and duplicate prevention
- Added profile image upload, replacement, and removal
- Added application-wide user notifications
- Improved authenticated user data protection
- Moved sensitive application values to environment variables
- Improved PostgreSQL database roles and application permissions
- Hardened the local database setup process
- Added automated DAO, controller, security, and integration testing
- Improved CORS configuration for local and deployed environments
- Continued improving responsive styling and usability

These changes allowed me to continue working with the same application while applying concepts I learned after the original capstone was completed.

---

## System Architecture

Jolt follows a traditional client-server architecture.

```text
Vue.js Frontend
       |
       | HTTP / REST
       v
Spring Boot Backend
       |
       | JDBC
       v
PostgreSQL Database

Spring Boot Backend
       |
       | HTTPS
       v
Yelp Fusion API
```

### Frontend

- Vue.js
- JavaScript
- HTML5
- CSS3
- Axios
- Vue Router
- Vuex

### Backend

- Java 11
- Spring Boot
- Spring Security
- RESTful API
- Spring JDBC
- JdbcTemplate
- JWT Authentication
- Maven

### Database

- PostgreSQL

### External Services

- Yelp Fusion API

---

## Technical Implementation

The Vue frontend communicates with the Spring Boot backend through RESTful HTTP requests.

The backend handles authentication, user-specific application data, database access, profile images, and communication with the Yelp Fusion API.

Some of the main technical concepts used throughout Jolt include:

- RESTful frontend and backend communication
- JWT authentication
- BCrypt password hashing
- Spring Security
- PostgreSQL relational database design
- DAO-based database access with JdbcTemplate
- Foreign key relationships and database constraints
- User-specific data access
- Multipart image uploads
- External API integration
- Environment-based application secrets
- Automated backend testing
- Responsive frontend design

---

## Database

Jolt uses PostgreSQL to store application and user-specific data.

The main database tables include:

```text
users
favorites
profile
image
```

User-owned records are connected to the application's users through foreign key relationships.

The database also includes constraints that support application rules, including preventing the same coffee shop from being saved more than once by the same user.

Jolt uses separate PostgreSQL roles for database ownership and normal application access. The Spring Boot application connects with a limited application account rather than the PostgreSQL administrator account.

---

## Security

Jolt uses Spring Security and JWT authentication to protect user-specific application functionality.

Security-related features include:

- BCrypt password hashing
- Stateless JWT authentication
- Protected backend endpoints
- Authenticated user lookup
- User-specific favorites, profiles, and profile images
- Environment variables for passwords, JWT secrets, and API keys
- Restricted application database permissions
- CORS configuration for approved frontend origins

The backend determines the current user from the authenticated request rather than accepting a user ID from the frontend for protected data operations.

---

## Testing

Jolt includes automated backend tests covering the application's database, controller, and security behavior.

Testing includes:

- User DAO integration tests
- Favorites DAO integration tests
- Profile DAO integration tests
- Image DAO integration tests
- Authentication controller tests
- Coffee search controller tests
- Favorites controller tests
- Profile controller tests
- Image controller tests
- JWT token tests
- JWT filter tests
- User details service tests
- Security utility tests
- Authentication and access-denied handler tests
- Web security integration tests

DAO integration tests use a separate PostgreSQL test database so database behavior can be tested without modifying the normal development database.

---

## Technology Stack

| Category | Technologies |
| --- | --- |
| Frontend | Vue.js, JavaScript, HTML5, CSS3, Axios |
| Backend | Java 11, Spring Boot, Spring Security, JDBC |
| Database | PostgreSQL |
| Authentication | JWT, BCrypt |
| API | Yelp Fusion API |
| Testing | JUnit, Spring Boot Test, Spring Security Test |
| Tools | Git, GitHub, Maven, IntelliJ IDEA |

---

## Project Structure

```text
jolt-coffee-shop-locator/

├── java/
│   ├── database/
│   ├── src/
│   │   ├── main/
│   │   └── test/
│   ├── pom.xml
│   └── README.md
├── vue/
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   ├── router/
│   │   ├── services/
│   │   ├── store/
│   │   └── views/
│   ├── package.json
│   └── README.md
├── screenshots/
├── .env.example
├── load-env.sh
└── README.md
```

The Java and Vue directories contain additional README files with more information about each side of the application.

---

## Screenshots

The following screenshots demonstrate the application after the independent redesign and continued development.

### Login Page

Secure user authentication for personalized application features.

![Login](screenshots/01-login.png)

---

### Home Page

Introduces Jolt and provides access to the application's main features.

![Home Page](screenshots/02-home.png)

---

### Coffee Shop Search

Displays nearby coffee shops retrieved through the Yelp Fusion API.

![Coffee Shop Search](screenshots/03-locator-search.png)

---

### Coffee Shop Results

Allows users to view coffee shop information and save locations to their favorites.

![Coffee Shop Results](screenshots/04-shop.png)

---

### Profile Management

Allows authenticated users to create and manage their profile information.

![Profile Management](screenshots/05-profile-form.png)

---

### Favorites

Displays coffee shops saved by the authenticated user.

![Favorites](screenshots/06-profile-favs.png)

---

## Running Jolt Locally

Jolt requires:

- Java 11
- PostgreSQL
- Node.js and npm
- A Yelp Fusion API key

Sensitive values are stored in a local `.env` file and are not committed to the repository.

An example environment file is included:

```text
.env.example
```

Copy the example file to `.env` and provide the required local values.

### Load Environment Variables

From the project root:

```bash
source load-env.sh
```

### Start the Backend

From the Java directory:

```bash
cd java
./mvnw spring-boot:run
```

The backend runs locally on:

```text
http://localhost:9000
```

### Start the Frontend

In a second terminal:

```bash
cd vue
npm install
npm run dev
```

The Vue development server runs locally on:

```text
http://localhost:5173
```

Additional setup information is available in the Java and Vue README files.

---

## Environment Variables

The root `.env.example` file documents the private environment values required by the application.

```env
DB_PASSWORD=
DB_ADMIN_PASSWORD=
JWT_BASE64_SECRET=
YELP_API_KEY=
```

The actual `.env` file should remain local and should never be committed or shared publicly.

---

## Live Demo

A live version of Jolt will be available at:

```text
https://jolt.jennifercurtis.me
```

Deployment information and final demo access will be added as the live version is completed.

---

## Future Enhancements

Jolt is intentionally maintained as one of my earlier full-stack projects, but there are several features that could be added in the future:

- Enhanced coffee shop search filtering
- Interactive map integration
- User reviews and ratings
- Search history
- Personalized coffee shop recommendations
- Additional accessibility and responsive design improvements

---

## Author

**Jennifer Curtis**

Business Systems & Technology Professional | Full-Stack Developer

**Portfolio:** https://jennifercurtis.me

**LinkedIn:** https://linkedin.com/in/jcurtisdeveloper

**GitHub:** https://github.com/CraftyCurtis05
