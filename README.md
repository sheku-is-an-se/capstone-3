# Video Game Store

The Video Game Store is a capstone e-commerce project that simulates a real-world online store for selling video games directly to customers. Built with a Spring Boot backend and MySQL database, the project emphasizes backend development skills such as debugging, API enhancement, feature implementation, and endpoint testing with Insomnia.

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
## Favorite Code

This is the `getCart` method — I like it because it shows how Spring Security passes the logged-in user straight into the method, no session needed.

```java
@GetMapping
public ShoppingCart getCart(Principal principal)
{
    String userName = principal.getName();
    User user = userService.getByUserName(userName);
    int userId = user.getId();
    return shoppingCartService.getByUserId(userId);
}
```

## Learning Journey

Building this project taught me how much a single missing annotation can break everything. I spent a lot of time debugging why endpoints were returning 401 instead of 403, or 404 instead of anything useful — and tracing those errors back to a missing `@PostMapping` or `@PreAuthorize` was frustrating at first, then satisfying once it clicked. Working with JWT authentication and role-based access control made me understand how real APIs actually protect their routes, not just in theory but in practice.