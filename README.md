# SmartShop - B2B Commercial Management Backend

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-brightgreen?style=for-the-badge&logo=spring-boot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![Swagger](https://img.shields.io/badge/Swagger-UI-green?style=for-the-badge&logo=swagger)

**SmartShop** is a robust B2B backend solution designed for managing clients, products, orders, and fidelity programs. Built as a **Modular Monolith** following **Domain-Driven Design (DDD)** principles, it provides a scalable and maintainable architecture for complex commercial operations.

---

## 🏗️ Architecture & Design

The project adopts a **Layered Architecture** (Controller → Service → Repository) organized by **Domain Modules**. This ensures separation of concerns and high cohesion within each business capability.

### Modules
- **Identity**: Manages Users, Authentication, and Authorization (RBAC).
- **Customer**: Handles Client profiles and Fidelity logic.
- **Catalog**: Manages Products, Stock, and Inventory.
- **Sales**: Processes Orders (`Commande`), Order Items, and Calculations (Tax, Discounts).
- **Billing**: Handles Payments, supporting fractional and multiple payment methods.

### Class Diagram
![UML Class Diagram](uml/class-diagram.png)

### Data Model Overview
- **User & Client**: The `User` entity is an abstract base class for authentication. `Client` extends `User`, adding business-specific fields like `fidelityLevel`, `totalSpent`, and contact info.
- **Product**: Represents catalog items with stock management and soft-delete capabilities.
- **Commande (Order)**: The central entity linking a `Client` to purchased items (`OrderItem`). It tracks status, tax calculations, and promo codes.
- **Paiement**: Linked to a `Commande`, allowing multiple payments (Fractional Payments) for a single order with different methods (Cash, Check, etc.).

---

## 🚀 Key Features

### 💎 Dynamic Loyalty System
SmartShop automatically calculates and updates client tiers (e.g., **Basic** → **Platinum**) based on their order history and total spend.
- Tracks `totalOrders` and `totalSpent`.
- Updates `fidelityLevel` in real-time.

### 💳 Fractional Payments
Flexible payment processing allows a single order to be paid via multiple transactions.
- Support for split payments (e.g., part Cash, part Check).
- Tracks `montantRestant` (remaining amount) on the Order.

### 🔒 Custom Security (RBAC)
Instead of standard Spring Security, this project implements a lightweight, custom Role-Based Access Control system:
- **`@RequireRole` Annotation**: Protects endpoints by specifying required roles (ADMIN, CLIENT, etc.).
- **Interceptors**: `AuthInterceptor` validates sessions and enforces role checks before requests reach controllers.
- **HttpSession**: Manages user sessions securely.

### 📦 Stock Management with Soft Delete
- **Real-time Stock Updates**: Inventory is adjusted upon order confirmation.
- **Soft Delete**: Implemented using Hibernate's `@SQLDelete` and `@SQLRestriction`. Deleted products remain in the database for integrity but are hidden from active queries.

---

## 🛠️ Tech Stack

_________________________________________
| Category     | Technology             |
|--------------|------------------------|
| **Language** | Java 17                |
| **Framework**| Spring Boot 3.2.0      |
| **Database** | MySQL                  |
| **ORM**      | Spring Data JPA        |
| **Mapping**  | MapStruct              |
| **Utils**    | Lombok                 |
| **Docs**     | Swagger UI (SpringDoc) |
_________________________________________

---

## 🏁 Getting Started

### Prerequisites
- **Java 17** SDK installed.
- **MySQL** Server running.
- **Maven** installed.

### Configuration
1. Clone the repository.
2. Navigate to `src/main/resources`.
3. Rename `application.properties.example` to `application.properties` (if applicable) or update the existing file with your DB credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/smartshop_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

### Running the Application
Run the following command in the root directory:
```bash
mvn spring-boot:run
```

### Data Seeder
The application includes a data seeder that initializes default users.
- **Admin Credentials**:
  - Username: `admin`
  - Password: `admin123`

---

## 📚 API Documentation

The API is fully documented using Swagger UI. Once the application is running, access the interactive documentation at:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

### Main Endpoints
- **Auth**: `/api/auth/login`, `/api/auth/register`
- **Clients**: `/api/clients` (CRUD, Fidelity stats)
- **Products**: `/api/products` (Catalog management)
- **Orders**: `/api/orders` (Place order, view history)
- **Payments**: `/api/payments` (Process payments)
