# API Documentation

## Endpoints

### Create Secret
- **URL**: `/api/secrets`
- **Method**: POST
- **Request Body**:
  ```json
  {
    "content": "string",
    "password": "string" (optional)
  }
  ```
- **Response**:
  ```json
  {
    "id": "string"
  }
  ```

### Check Secret
- **URL**: `/api/secrets/{id}/check`
- **Method**: GET
- **Response**:
  ```json
  {
    "requiresPassword": boolean
  }
  ```

### View Secret
- **URL**: `/api/secrets/{id}`
- **Method**: POST
- **Request Body**:
  ```json
  {
    "password": "string" (optional)
  }
  ```
- **Response**:
  ```json
  {
    "content": "string"
  }
  ```