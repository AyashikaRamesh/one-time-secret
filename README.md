# One-Time Secret Sharing Application

A secure way to share sensitive information that can only be viewed once. Once a secret is viewed, it is permanently deleted from the system, ensuring that sensitive information doesn't persist longer than necessary.

## Problem Statement

In today's digital world, sharing sensitive information securely is crucial. Sending passwords, API keys, or other confidential data through email or messaging apps leaves persistent copies of the data that could be compromised. This application solves this problem by:

1. Creating temporary, one-time-accessible secrets
2. Optional password protection for additional security
3. Automatic deletion after the first view
4. Clean and intuitive user interface

## Features

- **Create Secrets**: Share any text-based secret with optional password protection
- **One-Time Access**: Secrets are automatically deleted after being viewed
- **Password Protection**: Add an extra layer of security with password-protected secrets
- **Copy to Clipboard**: Easy copying of secret links
- **Modern UI**: Clean, responsive design with error handling
- **Secure Backend**: Spring Boot backend with in-memory database

## Prerequisites

### Frontend (Angular)
- Node.js (v14 or higher)
- npm (v6 or higher)
- Angular CLI (`npm install -g @angular/cli`)

### Backend (Spring Boot)
- Java 17 or higher
- Maven 3.6 or higher

## Setup and Running

### Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   The backend will start on port 8081.

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Run the development server:
   ```bash
   ng serve
   ```
   The frontend will be available at http://localhost:4200

## Technical Stack

### Frontend
- **Framework**: Angular 16
- **Language**: TypeScript
- **Styling**: CSS with modern design principles
- **HTTP Client**: Angular HttpClient for API communication

### Backend
- **Framework**: Spring Boot 3.1.0
- **Language**: Java 17
- **Database**: H2 (in-memory)
- **Build Tool**: Maven
- **API**: RESTful endpoints

## API Endpoints

- `POST /api/secrets` - Create a new secret
- `GET /api/secrets/{id}/check` - Check if a secret exists and if it requires a password
- `POST /api/secrets/{id}` - Retrieve and delete a secret

## Security Considerations

1. Secrets are stored securely in memory and are never written to disk
2. Passwords are required to be entered for protected secrets
3. Secrets are immediately deleted after viewing
4. CORS is configured for secure cross-origin requests
5. Input validation is implemented on both frontend and backend

## Future Enhancements

1. Secret expiration times
2. File attachment support
3. End-to-end encryption
4. User authentication
5. Audit logging
6. Multiple secret views with count limit

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
