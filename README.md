# EasyShop E-Commerce API

A full-stack e-commerce application with a Spring Boot REST API backend and a vanilla JavaScript frontend.

## Tech Stack

- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
- **Database:** MySQL
- **Auth:** JWT (JSON Web Tokens)
- **Frontend:** HTML, CSS, JavaScript

## Getting Started

### Prerequisites

- Java 17+
- MySQL 8+
- Maven

### Database Setup

1. Create a MySQL database named `videogamestore`
2. Update credentials in `backend/src/main/resources/application.properties` if needed:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/videogamestore
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### Running the Backend

```bash
cd backend
mvn spring-boot:run
```

The API runs on `http://localhost:8080`.

### Running the Frontend

Open `frontend/index.html` in a browser.

## API Endpoints

### Auth
| Method | URL | Access |
|--------|-----|--------|
| POST | `/register` | Public |
| POST | `/login` | Public |

### Categories
| Method | URL | Access |
|--------|-----|--------|
| GET | `/categories` | Public |
| GET | `/categories/{id}` | Public |
| GET | `/categories/{id}/products` | Public |
| POST | `/categories` | Admin |
| PUT | `/categories/{id}` | Admin |
| DELETE | `/categories/{id}` | Admin |

### Products
| Method | URL | Access |
|--------|-----|--------|
| GET | `/products` | Public |
| GET | `/products/{id}` | Public |
| POST | `/products` | Admin |
| PUT | `/products/{id}` | Admin |
| DELETE | `/products/{id}` | Admin |

### Shopping Cart
| Method | URL | Access |
|--------|-----|--------|
| GET | `/cart` | User |
| POST | `/cart/products/{productId}` | User |
| PUT | `/cart/products/{productId}` | User |
| DELETE | `/cart` | User |

## Authentication

Include the JWT token in the `Authorization` header for protected routes:

```
Authorization: Bearer <your_token>
```
