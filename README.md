# Account Management

## Project Overview

"Account Management" is a web application for managing bank accounts built with Spring Boot. The application supports
two roles: Administrator and User. The Administrator can block and unblock accounts, while the User can view their
account and perform deposit and withdrawal operations.

## Features

- **Administrator:**
    - Log in to the system.
    - View a list of all accounts.
    - Block and unblock accounts.

- **User:**
    - Log in to the system.
    - View only their own account.
    - Deposit and withdraw funds (if the account is not blocked).

## Technologies

- **Backend:**
    - Java 17
    - Spring Boot
    - Spring Security
    - Hibernate (JPA)
    - PostgreSQL

- **Frontend:**
    - HTML
    - Thymeleaf

- **Testing:**
    - JUnit
    - Mockito

- **Infrastructure:**
    - Docker
    - Docker Compose

## Prerequisites

- Java 17 or higher
- Docker and Docker Compose
- Maven

## Installation and Setup

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/account-management.git
cd account-management
```

### 2. Build and Run the Application

#### 1. Using Docker Compose

- Build and start the containers:

```bash
docker-compose up --build
```

This command will build the Docker images and start the containers for the Spring Boot application and PostgreSQL
database.

#### 2. Access the application:

The application will be running at http://localhost:8080.

#### 1. Using Maven

- Install dependencies and build the project:

```bash
mvn clean install
```

- Run the application:

```bash
mvn spring-boot:run
```

### 3. Accessing the Application

- Administrator Interface: http://localhost:8080/admin
- User Interface: http://localhost:8080/user

## Security

The application uses Spring Security for authentication and authorization. Passwords are hashed using BCrypt.


