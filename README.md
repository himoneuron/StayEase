# 🏨 StayEase: Hotel Booking API

A **RESTful API** service built with **Spring Boot** to manage a comprehensive hotel booking system, featuring robust role-based access control and **MySQL** for data persistence.

----------

## ✨ Core Features

-   **Hotel Management**: Full CRUD (Create, Update, Delete) operations for hotel listings, restricted to `Admin` and `Manager` roles.
    
-   **Booking Management**: Allows users to create, view, update, and cancel their bookings.
    
-   **User Management**: Supports user registration, profile updates, and account deletion.
    

----------

## 🔐 Role-Based Access Control (RBAC)

Access to API endpoints is strictly controlled based on user roles:

-   **`GET` Endpoints**: Accessible by `Customer`, `Admin`, and `Manager` roles.
    
-   **`PUT` Endpoints**: Accessible by `Admin` and `Manager` roles.
    
-   **`POST` & `DELETE` Endpoints**: Accessible only by the `Admin` role.
    

----------

## 🛠️ Technology Stack

-   **Spring Boot**: Facilitates rapid development and deployment of Java applications.
    
-   **Spring Data JPA**: Simplifies database operations with Object-Relational Mapping (ORM).
    
-   **Spring Web**: Supports building web applications using the MVC (Model-View-Controller) pattern.
    
-   **Spring Security**: Provides robust authentication and authorization capabilities.
    
-   **MySQL**: A reliable database management system for persistent data storage.
    
-   **Lombok**: Reduces boilerplate code by automatically generating getters, setters, and constructors.
    
-   **Swagger UI**: Provides interactive, browser-based documentation for the RESTful API.
