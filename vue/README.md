# Jolt Coffee Shop Locator — Vue Frontend

### Vue.js User Interface

This directory contains the Vue.js frontend for the Jolt Coffee Shop Locator.

The frontend provides the user interface for authentication, coffee shop searches, favorites, profile management, profile images, and the other user-facing areas of Jolt.

It communicates with the Java and Spring Boot backend through Axios and RESTful HTTP requests.

---

## Frontend Technologies

- Vue.js
- JavaScript
- HTML5
- CSS3
- Axios
- Vue Router
- Vuex
- Vite

---

## Project Structure

The main frontend code is located under:

```text
src/
```

The application is organized into:

```text
src/

├── assets/
├── components/
├── router/
├── services/
├── store/
├── views/
├── App.vue
└── main.js
```

### Main Frontend Areas

- **Assets** — Contains images and other static application resources.
- **Components** — Contains reusable interface components.
- **Router** — Defines application routes and protected navigation behavior.
- **Services** — Handles HTTP communication with the Spring Boot backend.
- **Store** — Maintains shared authentication and user state.
- **Views** — Contains the main application pages.
- **App.vue** — Provides the main application layout.
- **main.js** — Creates and configures the Vue application.

---

## Components

Jolt uses Vue components to separate the main parts of the user interface.

Components include areas for:

- Home page content
- Navigation
- Coffee shop search and results
- Favorites
- Profile information
- Profile pictures
- Application notifications
- Shared layout elements

Breaking the interface into components keeps individual files focused on a specific part of the application.

---

## Views

Views represent the main pages users navigate between.

Jolt includes views for:

- Login
- Registration
- Logout
- Home
- Coffee shop locator
- Profile
- Additional coffee-related content

Vue Router connects the application routes to these views.

---

## Routing

Application routes are configured in:

```text
src/router/
```

Vue Router controls navigation between the different Jolt pages.

Routes that contain protected user functionality require authentication.

Before navigating to a protected route, the application checks whether an authentication token is available.

If the user is not authenticated, they are redirected to the login page.

This prevents protected frontend pages from being accessed through normal application navigation without logging in.

Backend security provides the final protection for authenticated data and API endpoints.

---

## Authentication

The frontend works with the Spring Boot backend's JWT authentication system.

When a user successfully logs in:

1. The login information is sent to the backend.
2. The backend authenticates the account.
3. A JWT is returned to the frontend.
4. The token is stored for later authenticated requests.
5. Axios includes the token in the `Authorization` header.
6. The user is redirected into the authenticated application.

The frontend also stores the current user information so authenticated components can access the logged-in user's basic account data.

When the user logs out, the stored authentication information is cleared.

---

## Vuex Store

Jolt uses Vuex to manage shared authentication state.

The store maintains information such as:

```text
token
user
```

This allows authentication information to be shared between views and components without passing the same data through multiple component levels.

The authentication token is also stored locally so the user can remain authenticated when the page is refreshed.

---

## Services

Frontend service files handle communication with the Spring Boot backend.

Services keep Axios requests separate from the main Vue component templates.

The frontend sends requests for features such as:

- Login
- Registration
- Coffee shop searches
- Favorites
- Profile information
- Profile images

This keeps backend communication organized and makes it easier to see which part of the frontend is responsible for each API request.

---

## Coffee Shop Locator

The locator allows users to search for coffee shops using a location.

The frontend sends the location to the Spring Boot backend.

The backend communicates with the Yelp Fusion API and returns the business results.

The Vue interface then displays information such as:

- Business name
- Address
- Business image
- Yelp information
- Favorite controls

Users can save coffee shops they want to keep in their favorites.

---

## Favorites

Authenticated users can save coffee shops from the locator.

The favorites interface allows users to:

- Add a coffee shop to favorites
- View saved coffee shops
- Remove a saved coffee shop
- Open available business information

Favorite data is stored by the Spring Boot backend and PostgreSQL rather than only being stored in the browser.

This allows favorites to remain connected to the user's Jolt account.

---

## Profile Management

The profile interface allows authenticated users to create and update their personal profile information.

The initial profile form collects the user's profile information and birth date.

After the profile has been created, the interface changes to the update workflow.

The birth date is not shown as an editable field during later profile updates.

Profile data is saved through the Spring Boot backend and is associated with the authenticated account.

---

## Profile Pictures

The profile picture component allows users to:

- Upload a profile picture
- Replace the current picture
- Remove the current picture
- Display a fallback image when no profile picture is available

After a profile image changes, the frontend updates the displayed image so the user can see the result without needing to manually refresh the application.

---

## Application Notifications

Jolt uses a shared notification component for user-facing application messages.

Notifications are used for events such as:

- Successful updates
- Errors
- Warnings
- Informational messages

The notification component provides a consistent alternative to native browser alert messages.

It also uses appropriate accessibility roles so important messages can be announced to assistive technology.

---

## Responsive Design

The Jolt frontend includes responsive CSS for different screen sizes.

Responsive behavior is used throughout the application for areas such as:

- Navigation
- Page layouts
- Forms
- Coffee shop results
- Profile content
- Buttons
- Cards
- Images

Additional responsive testing and refinements can continue as the live version of the application is completed.

---

## Accessibility

The frontend includes accessibility considerations such as:

- Semantic HTML elements
- Form labels
- Image alternative text
- ARIA labels where needed
- Keyboard focus styling
- Accessible application notifications
- Descriptive buttons and links

Accessibility improvements are treated as an ongoing part of the frontend rather than a separate feature.

---

## Backend Communication

Axios is used for communication with the Spring Boot backend.

The frontend API location is controlled through:

```text
VITE_REMOTE_API
```

During local development, the frontend communicates with the backend running on:

```text
http://localhost:9000
```

The production value can be configured for the deployed Jolt backend.

Keeping the backend location in environment configuration allows the same frontend code to work in local and deployed environments.

---

## Local Setup

### 1. Install Dependencies

From the Vue directory:

```bash
npm install
```

### 2. Configure the Backend Location

The frontend environment configuration includes:

```env
VITE_REMOTE_API=
```

Configure this value as needed for the environment where the backend is running.

### 3. Start the Development Server

Run:

```bash
npm run dev
```

The Vue development server runs locally at:

```text
http://localhost:5173
```

The Spring Boot backend must also be running for features that require API or database access.

---

## Running the Full Application

Jolt requires both the frontend and backend during local development.

### Terminal 1 — Backend

From the project root:

```bash
source load-env.sh
cd java
./mvnw spring-boot:run
```

The backend runs at:

```text
http://localhost:9000
```

### Terminal 2 — Frontend

From the project root:

```bash
cd vue
npm install
npm run dev
```

The frontend runs at:

```text
http://localhost:5173
```

Open the frontend address in the browser to use Jolt.

---

## Frontend Design

Jolt is one of my earlier full-stack projects, and I have continued updating the frontend as my development skills have grown.

The frontend intentionally keeps a straightforward Vue structure using views, reusable components, services, Vue Router, and Vuex.

My goal with the continued redesign has been to improve the user experience and organization while keeping the code readable and understandable.

This also allows the project to show the progression from the original bootcamp capstone to the independently maintained version of Jolt.

---

## Related Documentation

For the complete project overview, project history, screenshots, and technology stack, see:

```text
../README.md
```

Backend-specific information is available in:

```text
../java/README.md
```
