# One-Time Secret Application Documentation

## Architecture Overview

The application follows a client-server architecture with clear separation of concerns:

### Frontend Architecture (Angular)

```
frontend/
├── src/
│   ├── app/
│   │   ├── services/
│   │   │   └── secret.service.ts    # Handles API communication
│   │   ├── secret-create/           # Secret creation component
│   │   │   ├── secret-create.component.ts
│   │   │   ├── secret-create.component.html
│   │   │   └── secret-create.component.css
│   │   ├── secret-view/            # Secret viewing component
│   │   │   ├── secret-view.component.ts
│   │   │   ├── secret-view.component.html
│   │   │   └── secret-view.component.css
│   │   ├── app.module.ts           # Main module configuration
│   │   └── app-routing.module.ts   # Route definitions
│   └── styles.css                  # Global styles
```

### Backend Architecture (Spring Boot)

```
backend/
├── src/main/
│   ├── java/com/example/onetimesecret/
│   │   ├── controller/
│   │   │   └── SecretController.java   # REST endpoints
│   │   ├── service/
│   │   │   └── SecretService.java      # Business logic
│   │   ├── repository/
│   │   │   └── SecretRepository.java   # Data access
│   │   ├── model/
│   │   │   └── Secret.java             # Data model
│   │   └── OneTimeSecretApplication.java
│   └── resources/
       └── application.properties        # Configuration
```

## Component Details

### Frontend Components

1. **SecretService** (`secret.service.ts`)
   - Handles all HTTP communication with backend
   - Methods:
     - `createSecret(content, password?)`: Creates new secret
     - `checkSecret(id)`: Checks secret existence and password requirement
     - `viewSecret(id, password?)`: Retrieves and deletes secret
   - Implements error handling for network and API errors

2. **SecretCreateComponent** (`secret-create/`)
   - Handles secret creation UI
   - Features:
     - Text input for secret content
     - Optional password protection
     - Copy to clipboard functionality
     - Success/error message display
   - Uses reactive forms for input handling

3. **SecretViewComponent** (`secret-view/`)
   - Manages secret viewing interface
   - Features:
     - Password input for protected secrets
     - One-time viewing confirmation
     - Error handling for invalid/expired secrets
   - Implements automatic secret deletion after viewing

### Backend Components

1. **SecretController** (`SecretController.java`)
   - REST API endpoints:
     - POST `/api/secrets`: Create secret
     - GET `/api/secrets/{id}/check`: Check secret status
     - POST `/api/secrets/{id}`: Retrieve secret
   - Handles request validation and response formatting

2. **SecretService** (`SecretService.java`)
   - Core business logic implementation
   - Features:
     - Secret creation with optional encryption
     - Password validation
     - One-time access enforcement
     - Automatic cleanup of viewed secrets

3. **Secret Model** (`Secret.java`)
   - Data structure for secrets
   - Fields:
     - id: Unique identifier
     - content: Encrypted secret content
     - password: Optional hashed password
     - createdAt: Timestamp
     - viewed: Access status flag

## Data Flow

1. **Secret Creation**:
   ```
   Frontend → SecretService → HTTP POST → Controller → Service → Repository → H2 Database
   ```

2. **Secret Retrieval**:
   ```
   Frontend → SecretService → HTTP POST → Controller → Service → Repository → Delete from DB
   ```

## Security Implementation

1. **Password Protection**
   - Frontend: Password input validation
   - Backend: BCrypt password hashing
   - API: Password verification before secret access

2. **One-Time Access**
   - Immediate deletion after viewing
   - No persistent storage of viewed secrets
   - Verification before deletion

3. **Data Security**
   - In-memory database usage
   - No plaintext password storage
   - CORS configuration for API security

## Error Handling

1. **Frontend Error Handling**
   - Network error detection
   - User-friendly error messages
   - Form validation
   - Loading states

2. **Backend Error Handling**
   - Global exception handling
   - Specific error responses
   - Validation checks
   - Proper HTTP status codes

## Configuration

1. **Frontend Configuration**
   - Environment-based API URL
   - Development/production modes
   - Angular routing setup

2. **Backend Configuration**
   - Server port (8081)
   - H2 database settings
   - CORS configuration
   - Logging setup

## Testing

The application includes:
- Unit tests for components
- Integration tests for API endpoints
- E2E tests for critical flows

## Deployment

1. **Frontend Deployment**
   - Build: `ng build --prod`
   - Output: `dist/` directory
   - Hosting: Any static file server

2. **Backend Deployment**
   - Build: `mvn clean package`
   - Output: Executable JAR
   - Hosting: Any Java-compatible server

## Monitoring & Maintenance

1. **Health Checks**
   - Backend health endpoint
   - Database connection monitoring
   - Memory usage tracking

2. **Logging**
   - Error logging
   - Access logging
   - Performance metrics

This documentation provides a comprehensive overview of the application's structure and implementation details for team reference.
