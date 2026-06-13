# RouteSphere Customer Shipment Self-Service Portal

## Project Overview

RouteSphere is a Logistics Management System prototype developed for the COMP70006 Software Development Practices Assessment 2. The developed module is the **Customer Shipment Self-Service Portal**, which allows customers to register, login, view shipments, track shipments, reschedule delivery, add special delivery instructions, and view shipment notifications.

This project demonstrates the use of software development practices such as Agile Scrum, lightweight DevOps, Secure SDLC, API testing, and component-based frontend development.

## Developed Module

**Module Name:** Customer Shipment Self-Service Portal
**System Name:** RouteSphere Logistics Management System

## Main Features

* Customer registration
* Customer login
* Customer dashboard
* View all customer shipments
* Track shipment using tracking ID
* View shipment status and delivery details
* Reschedule delivery date
* Add special delivery instructions
* View shipment notification messages
* Logout option

## Technology Stack

### Backend

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* MySQL
* Maven

### Frontend

* React
* Vite
* JavaScript
* CSS

### Database

* MySQL Server
* MySQL Workbench

### Development Tools

* Visual Studio Code
* Git and GitHub
* PowerShell
* Browser testing

## Project Structure

```text
CB016477SDPAssignment2
│
├── backend
│   ├── src
│   ├── pom.xml
│   └── target
│
├── frontend
│   ├── src
│   ├── package.json
│   └── vite.config.js
│
├── dBscripts
│   └── SQL scripts for database creation and sample data
│
├── docs
│   └── screenshots
│
├── DEVELOPMENT_LOG.md
└── README.md
```

## Prerequisites

Before running this project, install the following:

1. Java JDK 17
2. Maven
3. Node.js and npm
4. MySQL Server
5. MySQL Workbench
6. Visual Studio Code

Check installed versions using:

```powershell
java --version
mvn -v
node -v
npm -v
```

## Database Setup

1. Open **MySQL Workbench**.
2. Open the SQL script from the `dBscripts` folder.
3. Run the SQL script to create the database, tables, sample data, and database user.

The database used by this project is:

```text
logistics_shipment_db
```

The main tables used are:

```text
customers
shipments
delivery_reschedules
delivery_instructions
notifications
```

After running the SQL script, confirm that the tables and sample records are created successfully.

Example check:

```sql
USE logistics_shipment_db;

SELECT * FROM customers;
SELECT * FROM shipments;
SELECT * FROM notifications;
```

## Backend Configuration

The backend database connection is configured in:

```text
backend/src/main/resources/application.properties
```

Example configuration:

```properties
spring.application.name=logistics-backend
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/logistics_shipment_db
spring.datasource.username=logistics_app
spring.datasource.password=logistics@123

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

If your MySQL username or password is different, update this file before running the backend.

## How to Run the Backend

Open a terminal in the project root and run:

```powershell
cd backend
mvn spring-boot:run
```

The backend should start on:

```text
http://localhost:8080
```

Wait until the terminal shows:

```text
Tomcat started on port 8080
Started LogisticsBackendApplication
```

To check whether the backend is working, open this URL in the browser:

```text
http://localhost:8080/api/shipments/customer/1
```

If the backend is working, shipment data will be displayed as JSON.

## How to Run the Frontend

Open a second terminal in the project root and run:

```powershell
cd frontend
npm install
npm run dev
```

The frontend should start on:

```text
http://localhost:5173
```

Open this URL in the browser:

```text
http://localhost:5173
```

## Demo Login Details

Use the following demo customer account:

```text
Email: customer@example.com
Password: password123
```

After login, the customer can view the dashboard, shipment list, tracking page, reschedule form, instruction form, and notifications page.

## Main API Endpoints

### Authentication

```text
POST /api/auth/register
POST /api/auth/login
```

### Shipments

```text
GET /api/shipments/customer/{customerId}
GET /api/shipments/track/{trackingId}?customerId={customerId}
PUT /api/shipments/{shipmentId}/reschedule?customerId={customerId}
PUT /api/shipments/{shipmentId}/instructions?customerId={customerId}
```

### Notifications

```text
GET /api/notifications/customer/{customerId}
```

## Testing Completed

The following testing was completed:

* Customer registration test
* Customer login test
* Invalid login validation test
* Shipment list API test
* Shipment tracking API test
* Invalid tracking ID test
* Customer-specific shipment access control test
* Delivery reschedule test
* Past date validation test
* Special delivery instruction test
* Empty instruction validation test
* Notification list test
* Frontend browser testing
* Manual usability testing

Screenshots are available in:

```text
docs/screenshots
```

## Common Issues and Fixes

### 1. Frontend shows "Failed to fetch"

This means the frontend cannot connect to the backend.

Fix:

* Make sure the backend is running on port `8080`.
* Open this URL to test backend:

```text
http://localhost:8080/api/shipments/customer/1
```

If this does not show JSON data, start the backend again:

```powershell
cd backend
mvn spring-boot:run
```

### 2. Port 8080 already in use

If Spring Boot says port 8080 is already in use, run:

```powershell
netstat -ano | findstr :8080
```

Find the PID number and stop it:

```powershell
taskkill /PID YOUR_PID_NUMBER /F
```

Then start the backend again.

### 3. Database connection error

If the backend cannot connect to MySQL:

* Make sure MySQL Server is running.
* Open MySQL Workbench and confirm the database connection.
* Check username and password in `application.properties`.
* Confirm that `logistics_shipment_db` exists.

### 4. Frontend dependency error

If the frontend does not run, install dependencies again:

```powershell
cd frontend
npm install
npm run dev
```

## Build Commands

### Backend build

```powershell
cd backend
mvn clean package -DskipTests
```

### Frontend build

```powershell
cd frontend
npm run build
```

Both builds should complete successfully before final submission.

## Software Development Practices Applied

### Agile Scrum

The module was developed incrementally through sprint tasks such as backend setup, database setup, authentication API, shipment tracking API, reschedule API, instruction API, notification API, and frontend UI development.

### Lightweight DevOps

Git and GitHub were used for version control. Each major feature was committed separately using meaningful commit messages.

### Secure SDLC

The system includes password hashing, input validation, customer-specific shipment access, controlled error responses, and database access using a separate application database user.

### Testing

Backend API testing and frontend browser testing were completed. Validation and access-control scenarios were also tested.

### Maintainability

The backend uses a layered structure with controller, service, repository, entity, DTO, exception, and utility packages. The frontend uses reusable React components and a separate API service file.

## Author

Developed by Tharushi Shashimali - CB016477
COMP70006 Software Development Practices
Assessment 2 - Development and Demonstration
