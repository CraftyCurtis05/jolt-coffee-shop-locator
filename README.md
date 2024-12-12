"Jolt" - Coffee Shop Locator
(Currently under development)

This is a Coffee Shop Locator web application that allows users to find and explore coffee shops using the Yelp Fusion API. It offers an intuitive and dynamic user interface built with Vue.js, and provides seamless communication with the backend through Axios. The application is powered by a robust backend developed in Spring with JDBC for database interaction and PostgreSQL as the data store. The app includes user authentication, enabling users to create profiles, manage favorite coffee shops, and upload profile pictures.

Project Background <br>
This application was originally developed as a group capstone project. Since then, I have completely redesigned and refactored the entire codebase to improve its structure, performance, and functionality. The current version reflects my personal approach and enhancements, including updates to the user interface, backend architecture, and integration of modern development practices.

Features <br>
Coffee Shop Search: Search for coffee shops by location and other filters using the Yelp Fusion API. <br>
User Authentication: Users can sign up, log in, and manage their profiles. <br>
Favorites: Authenticated users can mark coffee shops as favorites, creating a personalized list of their preferred spots. <br>
Profile Management: Users can manage their profile information, including uploading a profile picture and updating their details. <br>
Profile Picture: Authenticated users can upload and display their profile pictures, giving them a personalized experience. <br>
Database Integration: User data and favorite coffee shop information are stored in a PostgreSQL database, with interactions facilitated by JDBC on the Spring backend. <br>
**Responsive Design: The frontend is fully responsive, ensuring a smooth experience on both mobile and desktop devices. <br>

Tech Stack <br>

Frontend: <br>
Vue.js: A progressive JavaScript framework used for building the user interface. <br>
Axios: A promise-based HTTP client for making API requests to both the Yelp Fusion API and the backend server. <br>

Backend: <br>
Spring: A comprehensive Java-based framework for building the server-side logic, including user authentication, and handling RESTful API requests. <br>
JDBC: Java Database Connectivity (JDBC) is used to interact with the PostgreSQL database for storing and retrieving user and coffee shop data. <br>

Database: <br>
PostgreSQL: A powerful open-source relational database system used to store user profiles, favorites, and related data. <br>

API Integration: <br>
Yelp Fusion API: Used to fetch coffee shop data including business details, location, reviews, and more. <br>

User Stories <br>
As a guest user, I can search for coffee shops by location. <br>
As a registered user, I can sign up, log in, and manage my profile. <br>
As a registered user, I can mark coffee shops as my favorites and view them on my profile page. <br>
As a registered user, I can upload a profile picture and update my profile information. <br>

Project Structure <br>
Frontend (Vue.js): Responsible for the user interface, handling the Yelp API integration, user authentication, and rendering coffee shop search results. <br>
Backend (Spring Boot): Handles user authentication, coffee shop data management, storing favorites, and managing database interactions with PostgreSQL via JDBC. <br>
Database (PostgreSQL): Stores user data, favorite coffee shops, and related profile information. <br>

Future Enhancements <br>
**User Reviews: Allow users to leave reviews and ratings for coffee shops. <br>
**Map Integration: Integrate a map (e.g., Google Maps) to display the coffee shops visually on a map. <br>
**Search History: Keep track of recent search history for users for easy access to previously searched locations. <br>

**Currently under development
