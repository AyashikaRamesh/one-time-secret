# Architecture Documentation

## Backend

### Technology Stack
- Spring Boot
- Spring Data JPA
- H2 Database

### Components
1. **Controller**
   - `SecretController`: Handles HTTP requests for creating, checking, and viewing secrets

2. **Service**
   - `SecretService`: Contains business logic for secret management

3. **Repository**
   - `SecretRepository`: JPA repository for database operations

4. **Model**
   - `Secret`: Entity class representing a secret
     - Fields: id, content, passwordHash, createdAt, viewed

## Frontend

### Technology Stack
- Angular
- Bootstrap for styling

### Components
1. **Secret Create**
   - Path: `/create`
   - Component: `SecretCreateComponent`
   - Features: Create new secrets with optional password

2. **Secret View**
   - Path: `/view/:id`
   - Component: `SecretViewComponent`
   - Features: View secret content, handle password protection

### Services
- `SecretService`: Handles API communication with backend

## Database Schema

### Secret Table
- `id` (String, Primary Key)
- `content` (String)
- `password_hash` (String, Nullable)
- `created_at` (DateTime)
- `viewed` (Boolean)