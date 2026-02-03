# E-Commerce Product Service

A comprehensive microservice for managing products, categories, orders, and search functionality in an e-commerce system with OAuth2/JWT authentication and role-based access control.

## Features

### ✅ Product Management
- **CRUD Operations**: Create, Read, Update, Delete products
- **Pagination**: Get products with pagination support
- **Filtering**: Filter products by category, price range
- **Search**: Full-text search across product titles and descriptions
- **Caching**: Redis caching for improved performance

### ✅ Category Management
- **CRUD Operations**: Manage product categories
- **Category-based filtering**: Get products by category

### ✅ Order Management
- **Create Orders**: Place orders with multiple products
- **Order Tracking**: View order status and details
- **Order Updates**: Update order status (Admin only)
- **Order Cancellation**: Cancel orders (with business rules)
- **User-specific Orders**: Users can only view their own orders

### ✅ Authentication & Authorization
- **JWT Token Validation**: Validates tokens with User Service
- **Role-Based Access Control (RBAC)**: 
  - **ADMIN**: Can create, update, delete products and manage all orders
  - **USER**: Can view products, create orders, and manage their own orders
- **Token-based Authentication**: Secure API endpoints with JWT tokens
- **Session Management**: Validates active sessions

### ✅ Search & Filtering
- **Full-text Search**: Search products by title and description
- **Category Filter**: Filter by category name
- **Price Range Filter**: Filter by minimum and maximum price
- **Pagination**: Search results with pagination

### ✅ API Documentation
- **Swagger/OpenAPI**: Interactive API documentation
- **JWT Authentication**: Swagger UI supports JWT token authentication

## Technology Stack

- **Framework**: Spring Boot 3.3.4
- **Java Version**: 21
- **Database**: MySQL 8.3
- **ORM**: JPA/Hibernate
- **Caching**: Redis
- **Search**: Elasticsearch (configured)
- **Security**: Spring Security with JWT
- **API Documentation**: Swagger/OpenAPI 3
- **Build Tool**: Maven

## API Endpoints

### Product Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| GET | `/products/{id}` | Get product by ID | No | - |
| GET | `/products` | Get all products | Yes | USER, ADMIN |
| GET | `/products/all/{pageNumber}` | Get paginated products | No | - |
| POST | `/products` | Create new product | Yes | ADMIN |
| PUT | `/products/{id}` | Update product | Yes | ADMIN |
| DELETE | `/products/{id}` | Delete product | Yes | ADMIN |

### Category Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/category` | Get all categories | No |
| GET | `/category/{name}` | Get category by name | No |
| POST | `/category` | Create category | No |

### Order Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| POST | `/orders` | Create new order | Yes | USER, ADMIN |
| GET | `/orders/{id}` | Get order by ID | Yes | USER, ADMIN |
| GET | `/orders` | Get user's orders | Yes | USER, ADMIN |
| GET | `/orders/all` | Get all orders | Yes | ADMIN |
| PUT | `/orders/{id}/status` | Update order status | Yes | USER, ADMIN |
| DELETE | `/orders/{id}` | Cancel order | Yes | USER, ADMIN |

### Search Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/search/products` | Search products | No |
| GET | `/search/products/category/{categoryName}` | Get products by category | No |

## Authentication

### JWT Token Format
The service expects JWT tokens in the following format:
- **Header**: `Authorization: Bearer <token>` OR `token: <token>`
- **Token Structure**: Standard JWT with header, payload, and signature

### Token Validation Flow
1. Client sends request with JWT token in header
2. Service extracts and decodes JWT payload
3. Service calls User Service to validate token and check session status
4. If valid and active, request proceeds; otherwise, returns 401/403

### Role-Based Access
- **ADMIN Role**: Full access to all operations
- **USER Role**: Can view products, create orders, manage own orders
- **Public Endpoints**: Product details, search, paginated products

## Configuration

### Application Properties
```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/productservice
spring.datasource.username=productservice

# User Service (Auth)
userservice.api.url=http://localhost:8081/auth
userservice.api.path.validate=/validate

# Redis Cache
spring.cache.type=REDIS
spring.data.redis.host=localhost
spring.data.redis.port=6379

# Elasticsearch
spring.elasticsearch.uris=http://localhost:9200
```

## Running the Application

### Prerequisites
- Java 21
- Maven 3.6+
- MySQL 8.0+
- Redis (optional, for caching)
- Elasticsearch (optional, for advanced search)

### Steps
1. **Start MySQL Database**
   ```bash
   # Create database
   CREATE DATABASE productservice;
   ```

2. **Start Redis** (optional)
   ```bash
   redis-server
   ```

3. **Start Elasticsearch** (optional)
   ```bash
   # Follow Elasticsearch installation guide
   ```

4. **Build and Run**
   ```bash
   cd ProductService
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access Swagger UI**
   ```
   http://localhost:8080/swagger-ui.html
   ```

## OAuth2/JWT Implementation

### Token Validation
The service implements OAuth2-style token validation:
1. **Token Extraction**: Extracts JWT from `Authorization: Bearer <token>` or `token: <token>` header
2. **Token Decoding**: Decodes JWT payload to extract user information
3. **Session Validation**: Calls User Service to validate token and check if session is ACTIVE
4. **Role Extraction**: Extracts user roles from JWT payload
5. **Authorization**: Checks user roles for endpoint access

### Security Flow
```
Client Request
    ↓
JWT Authentication Filter (extracts token)
    ↓
Token Validator (validates with User Service)
    ↓
Security Context (sets authentication)
    ↓
Controller (checks roles)
    ↓
Service Layer
```

### Example Request
```bash
# Get all products (requires authentication)
curl -X GET http://localhost:8080/products \
  -H "token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."

# Create product (requires ADMIN role)
curl -X POST http://localhost:8080/products \
  -H "token: <admin-token>" \
  -H "Content-Type: application/json" \
  -d '{"title":"New Product","price":{"value":99.99},"category":{"name":"Electronics"}}'
```

## Database Schema

### Product
- id (UUID)
- name, title, description, image
- price (OneToOne with Price)
- rating (OneToOne with Rating)
- category (ManyToOne with Category)
- orders (ManyToMany with Order)

### Category
- id (UUID)
- name
- products (OneToMany with Product)

### Order
- id (UUID)
- user (String - userId)
- status (OrderStatus enum)
- totalPrice
- products (ManyToMany with Product)

## Testing

### Integration Tests
- ProductControllerIT: Tests product endpoints
- Uses MockMvc for web layer testing
- Mocks UserServiceClient for token validation

### Running Tests
```bash
mvn test
```

## API Documentation

Once the application is running, access Swagger UI at:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## Error Handling

The service includes comprehensive error handling:
- **ProductNotFoundException**: 404 when product not found
- **CategoryNotFoundException**: 404 when category not found
- **InvalidTokenException**: 403 when token is invalid
- **IllegalArgumentException**: 400 for bad requests
- **SecurityException**: 403 for unauthorized access

## Notes

- Most linter errors are false positives from Lombok annotation processing
- The code compiles and runs correctly with Maven
- Ensure Lombok plugin is installed in your IDE for better code completion
- Token validation requires User Service to be running on port 8081

## Future Enhancements

- [ ] Payment integration
- [ ] Inventory management
- [ ] Product reviews and ratings
- [ ] Advanced Elasticsearch search
- [ ] Image upload functionality
- [ ] Product recommendations
- [ ] Analytics and reporting

