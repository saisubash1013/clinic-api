# Clinic Appointment API

A production-ready REST API built with Spring Boot and MongoDB.
Handles patient appointment management with JWT authentication.

## Tech Stack
- Java 23
- Spring Boot 3.4.3
- MongoDB 8.x
- JWT Authentication (jjwt 0.11.5)
- Maven

## Features
- Full CRUD for appointments
- JWT-based authentication (register/login)
- Input validation with proper error responses
- Custom MongoDB queries and aggregation pipelines
- Global exception handling with clean JSON error responses

## API Endpoints

### Auth (no token required)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/register | Register new user |
| POST | /auth/login | Login and receive JWT token |

### Appointments (Bearer token required)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /appointments | Get all appointments |
| POST | /appointments | Create new appointment |
| GET | /appointments/{id} | Get appointment by ID |
| PUT | /appointments/{id} | Update appointment |
| DELETE | /appointments/{id} | Delete appointment |
| GET | /appointments/doctor/{name} | Filter by doctor |
| GET | /appointments/status/{status} | Filter by status |
| GET | /appointments/search?status=X&doctor=Y | Filter by both |

### Stats (Bearer token required)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /stats/by-status | Appointment counts by status |
| GET | /stats/by-doctor | Appointment counts per doctor |
| GET | /stats/booked-by-doctor | Booked count per doctor |

## Running Locally

1. Make sure MongoDB is running:
```bash
mongod
```
2. Clone the repository:
```bash
git clone https://github.com/saisubash1013/clinic-api.git
```
3. Run the application:
```bash
./mvnw spring-boot:run
```
4. API available at `http://localhost:8080`

## Sample Requests

**Register:**
```json
POST /auth/register
{
  "username": "testuser",
  "password": "password123"
}
```

**Create Appointment (with token):**
```json
POST /appointments
Authorization: Bearer <your-token>
{
  "patientName": "John Smith",
  "doctorName": "Dr. Adams",
  "appointmentDate": "2026-03-20",
  "status": "BOOKED"
}
```

## Architecture
```
Client
  → JwtFilter (validates token on every request)
  → Controller (handles routing)
  → Service (business logic / aggregations)
  → Repository / MongoTemplate
  → MongoDB
```
