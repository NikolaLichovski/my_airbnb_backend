# My Airbnb Backend

A Spring Boot backend for an Airbnb-like booking platform. This application manages users, property listings, and bookings through RESTful APIs, forming a scalable base for a full-stack app.

## Features

- User management with registration and login  
- Property listings with hosts, countries, and availability  
- Booking and temporary reservation system  
- PostgreSQL persistence with Docker setup  
- REST API with OpenAPI/Swagger UI integration  
- Role-based access control (USER, HOST, ADMIN)  
- JWT-based stateless authentication  
- CORS enabled for frontend integration  

## Technology Stack

- Java 21, Spring Boot 3.4.4  
- Spring Data JPA, Hibernate  
- PostgreSQL 17.4 (via Docker)  
- Spring Security with JWT  
- SpringDoc OpenAPI for API documentation  
- Maven for build management  
- Lombok for boilerplate reduction  

## Setup Instructions

1. Clone the repository  
2. Run `docker-compose up -d` to start the PostgreSQL container  
3. Ensure port 2345 is free or adjust `application.properties` accordingly  
4. Build and run the Spring Boot app with your IDE or `mvn spring-boot:run`  
5. Access API docs at `http://localhost:8080/swagger-ui.html`  

## Configuration Highlights

- Database URL: jdbc:postgresql://localhost:2345/airbnb_db  
- User: airbnb  
- Password: airbnb  
- JWT Secret & expiration configured in `JwtConstants`  
- CORS allowed for `http://localhost:8080`  

## API Endpoints

- `/api/user/register` - Register new users (public)  
- `/api/user/login` - User login (public)  
- `/api/hosts` - Manage hosts (USER, HOST, ADMIN)  
- `/api/accommodations` - Property listings (USER, HOST, ADMIN)  
- `/api/availability` - Availability info (USER, HOST, ADMIN)  
- `/api/countries` - Country data (USER, HOST, ADMIN)  
- `/api/temp-reservations` - Temporary bookings (USER, HOST, ADMIN)  

## Security

- JWT token passed in Authorization header as Bearer token  
- Spring Security configured to secure endpoints based on roles  
- Stateless session management  

## Docker

- Uses official `postgres:17.4` image  
- Database initialized with SQL scripts in `init.sql`  
- Data persisted in Docker volume `pgdata`  

