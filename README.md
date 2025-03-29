# Parking POS Backend – Spring Boot Application

This repository contains the backend for the **Parking POS System**. It is built using **Spring Boot** and connects to a **MySQL database** to manage parking tickets, check-ins, and check-outs.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Backend Setup](#backend-setup)
  - [Install Dependencies](#install-dependencies)
  - [Configure MySQL Database](#configure-mysql-database)
  - [Run the Backend](#run-the-backend)
- [API Endpoints](#api-endpoints)
  - [Check-In](#check-in)
  - [Check-Out](#check-out)
- [Testing the Backend](#testing-the-backend)

## Prerequisites

Before setting up the backend, ensure you have the following installed on your system:
- **Java 8 or later** (for the backend).
- **Maven** (or **Gradle**) for building the project.
- **MySQL** for the database.

## Backend Setup

### 1. Install Dependencies
This project uses **Spring Boot** to create a RESTful backend.

#### a. **Install Java 8 or later**
- Follow the instructions to install Java on your system:  
  - [Oracle Java Download](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
  
#### b. **Install Maven (or Gradle)**
- If you're using **Maven**, install it from [Maven's Website](https://maven.apache.org/install.html).

### 2. Configure MySQL Database
1. **Install MySQL** (if not already installed).
   - **Windows**: [Download MySQL Installer](https://dev.mysql.com/downloads/installer/).
   - **macOS**: Install MySQL using **Homebrew**: 
     ```bash
     brew install mysql
     ```
   - **Linux (Ubuntu)**:
     ```bash
     sudo apt update
     sudo apt install mysql-server
     ```

2. **Create the Database**:
   - Log in to MySQL:
     ```bash
     mysql -u root -p
     ```
   - Create the **database**:
     ```sql
     CREATE DATABASE parking_pos_db;
     ```
   - Create a **user** (optional) and grant privileges:
     ```sql
     CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';
     GRANT ALL PRIVILEGES ON parking_pos_db.* TO 'username'@'localhost';
     FLUSH PRIVILEGES;
     ```

3. **Configure the `application.properties`**:
   - In the **Spring Boot** project folder, go to `src/main/resources/application.properties`.
   - Update the MySQL connection details:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/parking_pos_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

### 3. Run the Backend
1. Navigate to the **backend** folder (e.g., `parking-pos-backend/`).
2. Build the project with Maven:
   ```bash
   mvn clean install
   ```
3. Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```
4. The backend API should now be running on http://localhost:8080.

## API Endpoints
### Check-In Endpoint
URL: POST /api/tickets/checkin

Request Body:
```json
{
  "plateNumber": "ABC1234"
}
```

### Check-Out Endpoint
URL: POST /api/tickets/checkout

Request Body:
```json
{
  "plateNumber": "ABC1234"
}
```

## Testing the Backend
You can test the backend using Postman or cURL by sending requests to the API endpoints.

Example for Check-In:

``` bash
curl -X POST http://localhost:8080/api/tickets/checkin -H "Content-Type: application/json" -d '{"plateNumber":"ABC1234"}'
```