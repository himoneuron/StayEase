StayEase
Problem Statement
Develop a RESTFul API service using Spring Boot to manage a hotel booking system with role-based access control and MySQL for data persistence.

Features
Hotel Management: Create, Update, Delete hotels (Admin and Manager roles).
Booking Management: Create, Update, Delete bookings.
User Management: Register, Update, Delete users.
Role-Based Access Control:
GET endpoints: Accessible by Customer, Admin, and Manager roles.
POST and DELETE endpoints: Accessible by Admin role.
PUT endpoints: Accessible by Admin and Manager roles.
Technologies Used
Spring Boot: Facilitates rapid development and deployment of Java applications.
Spring Data JPA: Simplifies database operations with ORM (Object-Relational Mapping) capabilities.
Spring Web: Supports building web applications using the MVC (Model-View-Controller) pattern.
Spring Security: Provides authentication and authorization capabilities.
MySQL: Database management system used for persistent data storage.
Lombok: Reduces boilerplate code by automatically generating getters, setters, constructors, etc.
Swagger UI: Provides interactive documentation for RESTFul APIs.
