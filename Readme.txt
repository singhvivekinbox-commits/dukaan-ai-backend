# AI SaaS Backend (Spring Boot)

## Overview
This is a production-ready backend structure for WhatsApp AI SaaS platform.

## Tech Stack
- Java 17
- Spring Boot (latest)
- PostgreSQL
- Redis (future)
- OpenAI (AI layer)

## Folder Structure

### config/
Configuration classes (security, beans)

### controller/
REST APIs for frontend

### webhook/
Handles WhatsApp incoming messages

### service/
Core business logic
- core/ → business logic
- ai/ → AI integration
- external/ → third-party APIs

### repository/
Database access layer

### model/
JPA entities

### dto/
Request/Response objects

### mapper/
Entity to DTO conversion

### util/
Utility/helper classes

### exception/
Global exception handling

## How to Run
1. Install Java 17
2. Run: ./mvnw spring-boot:run

## Future Enhancements
- Microservices split
- Kafka integration
- AI optimization
