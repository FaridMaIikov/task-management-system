# TASK-MANAGER-SYSTEM
Task management system is a demonstration web application developed for
team project works where users can manage various tasks within a team easily.

## Prerequisites

- Java 17
- Spring Boot 3.0.8
- Gradle
- IDE-IntelliJ
- DB-PostgreSQL
- Liquibase

## Installation
Clone the repository:

git clone https://github.com/yourusername/task-manager-system.git
cd task-manager-system

Running the Application
Run the application using your preferred method. For example, if you're using Gradle:

./gradlew bootRun

The application should now be accessible at http://localhost:8080.

# API Endpoints

## Category Service

- POST - api/v1/categories/create
- GET - api/v1/categories
- GET - api/v1/categories/{{id}}
- GET - api/v1/categories/name
- PUT - api/v1/categories/update/{{id}}
- DELETE - api/v1/categories/delete/{{id}}

## Task Service

- POST - api/v1/tasks/create
- GET - api/v1/tasks/{{id}}
- GET - api/v1/tasks/name
- GET - api/v1/tasks/status
- GET - api/v1/tasks
- DELETE - api/v1/tasks/delete/{{id}}
- PATCH - api/v1/tasks/update/{{id}}
- PATCH - api/v1/tasks/update/change-status/{{id}}


# Documentation
## Swagger API
Explore and interact with the API using Swagger UI. Access the Swagger UI at:

http://localhost:8080/swagger-ui.html
