# Sales Management System - Spring Boot MVC Framework

A complete MVC framework for sales management system built with Spring Boot, following best practices and including comprehensive testing.

## 🏗️ Architecture Overview

This application follows the **Model-View-Controller (MVC)** architectural pattern with the following layers:

### Model Layer
- **Entities**: JPA entities representing database tables (Product, Customer, Order, Invoice, Inventory)
- **DTOs**: Data Transfer Objects for API communication
- **Repositories**: Spring Data JPA repositories for data access
- **Services**: Business logic layer with interface-implementation pattern

### View Layer
- **Thymeleaf Templates**: Server-side rendering with responsive design
- **Bootstrap 5**: Modern, responsive UI framework
- **Custom CSS**: Additional styling for enhanced user experience

### Controller Layer
- **Web Controllers**: Handle HTTP requests and return views
- **REST Controllers**: Provide API endpoints for frontend integration
- **Exception Handling**: Global error handling and validation

## 📂 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/banhang/
│   │       ├── BanHangApplication.java          # Main Spring Boot application
│   │       ├── controller/
│   │       │   ├── web/                         # Web controllers for UI
│   │       │   │   ├── DashboardController.java
│   │       │   │   └── ProductController.java
│   │       │   └── api/                         # REST API controllers
│   │       ├── model/
│   │       │   ├── entity/                      # JPA entities
│   │       │   │   ├── Product.java
│   │       │   │   ├── Customer.java
│   │       │   │   ├── Order.java
│   │       │   │   ├── OrderItem.java
│   │       │   │   ├── Invoice.java
│   │       │   │   ├── Inventory.java
│   │       │   │   ├── OrderStatus.java         # Enums
│   │       │   │   ├── PaymentStatus.java
│   │       │   │   └── PaymentMethod.java
│   │       │   └── dto/                         # Data Transfer Objects
│   │       │       ├── ProductDTO.java
│   │       │       ├── CustomerDTO.java
│   │       │       ├── OrderDTO.java
│   │       │       ├── OrderItemDTO.java
│   │       │       ├── InvoiceDTO.java
│   │       │       ├── InventoryDTO.java
│   │       │       └── DashboardDTO.java
│   │       ├── repository/                      # Spring Data JPA repositories
│   │       │   ├── ProductRepository.java
│   │       │   ├── CustomerRepository.java
│   │       │   ├── OrderRepository.java
│   │       │   ├── OrderItemRepository.java
│   │       │   ├── InvoiceRepository.java
│   │       │   └── InventoryRepository.java
│   │       ├── service/
│   │       │   ├── interfaces/                  # Service interfaces
│   │       │   │   ├── ProductService.java
│   │       │   │   ├── CustomerService.java
│   │       │   │   ├── OrderService.java
│   │       │   │   ├── InvoiceService.java
│   │       │   │   ├── InventoryService.java
│   │       │   │   └── DashboardService.java
│   │       │   └── impl/                        # Service implementations
│   │       │       ├── ProductServiceImpl.java
│   │       │       └── CustomerServiceImpl.java
│   │       └── exception/                       # Custom exceptions
│   └── resources/
│       ├── templates/                           # Thymeleaf templates
│       │   ├── layout/
│       │   │   └── main.html                   # Main layout template
│       │   ├── dashboard.html                  # Dashboard view
│       │   └── product/
│       │       └── list.html                   # Product list view
│       ├── static/                             # Static resources
│       │   ├── css/                           # Custom stylesheets
│       │   ├── js/                            # JavaScript files
│       │   └── images/                        # Images
│       └── application.properties              # Application configuration
└── test/
    └── java/
        └── com/banhang/
            └── service/
                └── impl/
                    └── ProductServiceImplTest.java  # Unit tests
```

## 🚀 Features

### Implemented Features
- ✅ **Dashboard**: Statistics overview with system status
- ✅ **Product Management**: Complete CRUD operations with search and pagination
- ✅ **Responsive Design**: Bootstrap-based UI that works on all devices
- ✅ **Data Validation**: Input validation with error handling
- ✅ **Database Integration**: H2 in-memory database with JPA/Hibernate
- ✅ **Unit Testing**: Comprehensive test coverage with Mockito
- ✅ **Layered Architecture**: Clean separation of concerns

### Planned Features
- 🔄 Customer Management
- 🔄 Order Management  
- 🔄 Invoice Generation
- 🔄 Inventory Management
- 🔄 REST API endpoints
- 🔄 Advanced search and filtering
- 🔄 Reports and analytics
- 🔄 User authentication and authorization

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Java Version**: Java 17
- **Database**: H2 (development), configurable for production
- **ORM**: Hibernate/JPA
- **Template Engine**: Thymeleaf
- **CSS Framework**: Bootstrap 5
- **Build Tool**: Maven
- **Testing**: JUnit 5, Mockito
- **Code Coverage**: JaCoCo

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- IDE with Spring Boot support (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/nthagg03/OOP_NO2_T3_2_2025_GroupA.git
cd OOP_NO2_T3_2_2025_GroupA
```

### 2. Build the Project
```bash
mvn clean compile
```

### 3. Run Tests
```bash
mvn test
```

### 4. Start the Application
```bash
mvn spring-boot:run
```

### 5. Access the Application
- **Web Interface**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:banhang`
  - Username: `sa`
  - Password: (leave empty)

## 🧪 Testing

### Running Tests
```bash
# Run all tests
mvn test

# Run tests with coverage report
mvn clean test jacoco:report
```

### Test Coverage
The project includes:
- **Unit Tests**: Service layer testing with Mockito
- **Integration Tests**: Repository layer testing (planned)
- **Test Coverage Reports**: Generated in `target/site/jacoco/`

## 📊 Database Schema

The application uses the following main entities:

- **Products**: Product catalog with pricing and inventory
- **Customers**: Customer information and contact details
- **Orders**: Sales orders with multiple items
- **OrderItems**: Individual items within an order
- **Invoices**: Billing and payment tracking
- **Inventory**: Stock management and warehouse operations

## 🔧 Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:h2:mem:banhang
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Thymeleaf Configuration
spring.thymeleaf.cache=false
```

### Development vs Production
- **Development**: Uses H2 in-memory database
- **Production**: Configure with MySQL/PostgreSQL
- **Profiles**: Use Spring profiles for environment-specific configuration

## 📈 Performance Considerations

- **Pagination**: Implemented for large datasets
- **Lazy Loading**: JPA entities use appropriate fetch strategies
- **Connection Pooling**: HikariCP for database connections
- **Caching**: Ready for Redis/Hazelcast integration

## 🔒 Security (Planned)

- Spring Security integration
- JWT token authentication
- Role-based access control (RBAC)
- Input validation and sanitization

## 📝 API Documentation (Planned)

- Swagger/OpenAPI integration
- Interactive API documentation
- Request/response examples

## 🚀 Deployment

### Development
```bash
mvn spring-boot:run
```

### Production
```bash
mvn clean package
java -jar target/ban-hang-system-0.0.1-SNAPSHOT.jar
```

### Docker (Planned)
```bash
docker build -t sales-management .
docker run -p 8080:8080 sales-management
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is part of an academic assignment for Object-Oriented Programming course.

## 👥 Team Members

| Name | Student ID | GitHub | Role |
|------|------------|---------|------|
| Nguyễn Xuân Thắng | 24100529 | [nthagg03](https://github.com/nthagg03) | Team Leader |
| Vũ Văn Phương | 24100373 | [mphw0312](https://github.com/mphw0312) | Developer |

## 🎓 Academic Information

- **Course**: Object-Oriented Programming (OOP)
- **Instructor**: TS. Nguyễn Lệ Thu
- **Institution**: K18 - Term 3 - 2025
- **Project Type**: Final Term Project

## 📞 Support

For support and questions, please open an issue in the GitHub repository or contact the team members directly.