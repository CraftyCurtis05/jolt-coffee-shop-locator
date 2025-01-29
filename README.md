# "Jolt" - Coffee Shop Locator

Welcome to the Coffee Shop Locator web application! This app allows users to find and explore coffee shops using the **Yelp Fusion API**. It offers an intuitive and dynamic user interface built with **Vue.js**, and provides seamless communication with the backend through **Axios**. The application is powered by a robust backend developed in **Spring** with **JDBC** for database interaction and **PostgreSQL** as the data store. The app includes user authentication, enabling users to create profiles, manage favorite coffee shops, and upload profile pictures.

---

## Introduction

This project was initially developed as part of a collaborative effort but has since been fully redesigned and refactored by me to enhance its structure, performance, and functionality. The app now reflects my personal approach, featuring an improved user interface, a reworked backend architecture, and modern development practices. It helps users discover nearby coffee shops, manage their profiles, and save their favorite spots using data from the **Yelp Fusion API** and a **PostgreSQL** database.

---

## Features
- **Coffee Shop Search**: Search for coffee shops by location and other filters using the **Yelp Fusion API**.
- **User Authentication**: Users can sign up, log in, and manage their profiles.
- **Favorites**: Authenticated users can mark coffee shops as favorites, creating a personalized list of their preferred spots.
- **Profile Management**: Users can manage their profile information, including uploading a profile picture and updating their details.
- **Profile Picture**: Authenticated users can upload and display their profile pictures, providing a more personalized experience.
- **Database Integration**: User data and favorite coffee shop information are stored in a **PostgreSQL** database, with interactions facilitated by **JDBC** on the **Spring** backend.
- **Responsive Design**: The frontend is fully responsive, ensuring a smooth experience on both mobile and desktop devices.

---

## Project Structure
The application is built using **Vue.js** for the frontend and **Spring Boot** for the backend. The project structure is organized as follows:

/coffee-shop-locator
    /backend
        /src
            /main
                /java/com/coffee/locator
                    /controller
                    /dao
                    /dto
                    /entity
                    /repository
                    /security
                    /service
                    CoffeeShopLocatorApplication.java
                /resources
                    /application.yml
    /frontend
        /src
            /components
            /services
            /views
            App.vue
            main.js
        /public
            index.html

---

## Technologies Used
**Frontend**:
- **Vue.js**: **JavaScript** framework used for building the user interface.
- **Axios**: Promise-based HTTP client for making API requests to the backend server.

**Backend**:
- **Spring Boot**: **Java**-based framework for developing the backend logic, handling user authentication, and managing RESTful API requests.
- **JDBC**: Java Database Connectivity used to interact with the **PostgreSQL** database for storing and retrieving user and coffee shop data.
  
**Database**:
- **PostgreSQL**: A powerful open-source relational database system for storing user profiles, favorites, and related data.

**API Integration**:
- **Yelp Fusion API**: External API used to search and retrieve coffee shop data.

---

## Prerequisites
Before you start, make sure you have the following installed:

A code editor (e.g., **VS Code**, **IntelliJ IDEA**)
Ensure you have **Node.js** installed, including **npm**.
**Java 11** or newer
**PostgreSQL** (or any compatible relational database)
**Maven** (for backend)
Retrieve an API key from **Yelp Fusion API**

---

## Acknowledgements

- **Yelp Fusion API**: For providing the coffee shop data.
- **Vue.js**: For building the dynamic and interactive frontend.
- **Spring Boot**: For providing a powerful framework for backend development.
- **PostgreSQL**: For being a reliable database system for storing user and coffee shop data.

---

## Future Enhancements
<em>Currently under development</em> <br>
- **User Reviews**: Allow users to leave reviews and ratings for coffee shops.
- **Map Integration**: Integrate a map (e.g., **Google Maps**) to display the coffee shops visually on a map.
- **Search History**: Keep track of recent search history for users for easy access to previously searched locations.
