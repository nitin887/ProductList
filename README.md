# Product Service API

A Spring Boot-based RESTful API service for managing products and categories. This service provides comprehensive product management capabilities with security, documentation, and monitoring features.

## Technologies Used

- **Java**: JDK 24
- **Framework**: Spring Boot 3.5.7
- **Database**: MySQL (with H2 for local development)
- **Security**: Spring Security with JWT & OAuth2
- **Documentation**: OpenAPI (Swagger) 2.8.9
- **Build Tool**: Maven
- **Additional Features**:
  - Spring Actuator for monitoring
  - Lombok for boilerplate reduction
  - Spring Data JPA for database operations
  - Logback for logging
  - HikariCP for connection pooling

## Prerequisites

- JDK 24 or higher
- Maven 3.x
- MySQL Server 8.x
- Docker (optional, for containerization)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/nitin887/ProductList.git
cd ProductList
```

### 2. Configure Database

The application uses MySQL by default. Update the database configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
```

For local development, the application will automatically switch to H2 (in-memory database) if no profile is specified.

### 3. Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access the OpenAPI documentation at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Features

- Product management (CRUD operations)
- Category management
- JWT-based authentication
- Role-based access control
- API documentation with OpenAPI
- Health monitoring with Spring Actuator
- Connection pool management with HikariCP
- Comprehensive logging with Logback

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/bitsnbyte/productlist/
│   │       ├── controller/    # REST controllers
│   │       ├── service/       # Business logic
│   │       ├── repository/    # Data access
│   │       ├── entity/        # Database entities
│   │       ├── dto/          # Data transfer objects
│   │       ├── security/     # Security configurations
│   │       ├── exception/    # Exception handling
│   │       └── mapper/       # Object mappers
│   └── resources/
│       ├── application.properties  # Main configuration
│       └── logback-spring.xml     # Logging configuration
└── test/
    └── java/                      # Test classes
```

## Security

The application uses Spring Security with JWT tokens and OAuth2 resource server capabilities. Protected endpoints require authentication.

## Monitoring

Spring Actuator endpoints are enabled for monitoring. Access them at:
```
http://localhost:8080/actuator/health    # Health information
http://localhost:8080/actuator/info      # Application information
```

All actuator endpoints are exposed and detailed health information is available.

## Logging

- Logging is configured using Logback
- Log files are generated in the `logs` directory
- Different log levels are configured for different environments

## Database Configuration

### HikariCP Settings
- Maximum pool size: 10
- Minimum idle connections: 2
- Idle timeout: 600000ms (10 minutes)
- Connection timeout: 30000ms (30 seconds)
- Maximum lifetime: 1800000ms (30 minutes)

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact



Project Link: https://github.com/nitin887/ProductList
