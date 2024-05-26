# Medical Application

## Overview

Welcome to the medical-Application repository developed by **Sreyas Patil**. This Java Spring Boot project facilitates efficient management of products, customer orders, employee attendance, and admin operations.

## Technologies Used

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **Spring Web**
- **MySQL Connector/J**
- **Spring Security Crypto**
- **Lombok**
- **Maven**

## Project Structure

### Controllers

- **AdminRestController.java**: Manages admin operations and endpoints.
- **AttendanceRestController.java**: Handles attendance-related HTTP requests.
- **CartItemRestController.java**: Controls shopping cart item operations.
- **CustomerRestController.java**: Manages customer-related HTTP requests.
- **EmployeeRestController.java**: Handles employee operations and endpoints.
- **OrderRestController.java**: Manages order operations and endpoints.
- **ProductRestController.java**: Controls product-related HTTP requests.
- **UtilityRestController.java**: Provides utility endpoints for image encoding/decoding.

### Models

- **Admin.java**: Represents the Admin entity.
- **Attendance.java**: Represents the Attendance entity.
- **CartItem.java**: Represents the CartItem entity.
- **Customer.java**: Represents the Customer entity.
- **Employee.java**: Represents the Employee entity.
- **Order.java**: Represents the Order entity.
- **Product.java**: Represents the Product entity.

### Repositories

- **AdminRepository.java**: Provides database operations for Admin entities.
- **AttendanceRepository.java**: Provides database operations for Attendance entities.
- **CartItemRepository.java**: Provides database operations for CartItem entities.
- **CustomerRepository.java**: Provides database operations for Customer entities.
- **EmployeeRepository.java**: Provides database operations for Employee entities.
- **OrderRepository.java**: Provides database operations for Order entities.
- **ProductRepository.java**: Provides database operations for Product entities.

### Service Implementations

- **AdminServiceImpl.java**: Implements business logic for Admin operations.
- **AttendanceServiceImpl.java**: Implements business logic for Attendance operations.
- **CartItemServiceImpl.java**: Implements business logic for CartItem operations.
- **CustomerServiceImpl.java**: Implements business logic for Customer operations.
- **EmployeeServiceImpl.java**: Implements business logic for Employee operations.
- **OrderServiceImpl.java**: Implements business logic for Order operations.
- **ProductServiceImpl.java**: Implements business logic for Product operations.

### Utilities

- **ImageDecoder.java**: Provides utility methods for decoding base64 encoded images.
- **ImageEncoder.java**: Provides utility methods for encoding images to base64 format.

### Application Entry Point

- **ProductsDescApplication.java**: Main class to start the Spring Boot application.

### Configuration

- **application.properties**: Configuration file for application properties.

## Installation

To run this application locally, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/sreyaspatil5/medical-Application.git
   cd medical-Application
   ```

2. Configure MySQL database in `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/products_desc
   spring.datasource.username=root
   spring.datasource.password=root
   ```

3. Build and run the application using Maven:

   ```bash
   mvn spring-boot:run
   ```

4. Access the application at [http://localhost:8080](http://localhost:8080).

## Usage

- **Product Management**: CRUD operations for products including image upload.
- **Customer Management**: Sign up and sign in functionality for customers.
- **Order Management**: Create, update, and delete orders.
- **Employee Attendance**: Mark attendance, view attendance summary by month and year.
- **Admin Operations**: Create and retrieve admin credentials.

## Additional Notes

- Ensure MySQL server is running and configured correctly.
- Customize application properties such as server port, database credentials, etc., as per your environment.

## Contributors

- **Sreyas Patil** - Lead Developer

## License

This project is licensed under the Apache 2.0 License.
