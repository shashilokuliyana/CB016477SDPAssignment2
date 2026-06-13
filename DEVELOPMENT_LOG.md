# Development Log - Logistics Shipment Self-Service Portal

## Selected Module
Customer Shipment Self-Service Portal

## Sprint 1 Goal
Develop a working customer-facing shipment module that supports customer login, shipment tracking, delivery rescheduling, special delivery instructions, and notification viewing.

## Software Development Practices Applied
1. Agile Scrum - user stories, use cases, product backlog, sprint backlog, incremental delivery.
2. Lightweight DevOps - Git version control, structured commits, README, repeatable setup steps.
3. Automated Testing - API testing using Postman and selected backend automated tests.
4. Secure SDLC - login, password hashing, input validation, customer-specific shipment access, and controlled error messages.

## Initial Project Structure
- backend
- frontend
- docs
- README.md
- DEVELOPMENT_LOG.md
- .gitignore

## Sprint 1 Status
Project folder and documentation structure created.

## Git Evidence
Initial Git repository created and first commit completed for the project structure.

## Sprint 1 Progress Update

### S1-T01 - Create Spring Boot Backend Project Structure
Status: Completed

Evidence:
- Spring Boot backend project generated using Spring Initializr.
- Backend folder contains pom.xml, src folder, Maven wrapper files, and configuration files.
- Maven build checked using `mvn clean package -DskipTests`.
- Git commit completed: "Add Spring Boot backend project skeleton".

Practice Mapping:
- Agile Scrum: Completed Sprint 1 task S1-T01.
- Lightweight DevOps: Backend skeleton added to Git using a meaningful commit.
- Maintainability: Spring Boot structure prepared for controller, service, repository, entity, DTO, and exception layers.

### S1-T02 - Create React Frontend Project Structure
Status: Completed

Evidence:
- React frontend project created using Vite.
- Frontend dependencies installed using npm.
- React development server started successfully on port 5173.
- Initial React page opened successfully in the browser.
- Frontend skeleton committed to Git.

Practice Mapping:
- Agile Scrum: Completed Sprint 1 task S1-T02.
- Lightweight DevOps: React frontend structure was added to version control using a meaningful Git commit.
- Maintainability: Frontend project structure was prepared for page and component-based development.
- Usability: The frontend will be developed as a browser-based customer portal.


### S1-T03A - Create MySQL Database and Required Tables
Status: Completed

Evidence:
- MySQL Server and MySQL Workbench installed successfully.
- Database `logistics_shipment_db` created.
- Tables created: customers, shipments, notifications.
- Sample customer, shipment, and notification records inserted.
- Application database user `logistics_app` created instead of using the root user directly.

Practice Mapping:
- Agile Scrum: Completed Sprint 1 task S1-T03.
- Secure SDLC: Separate application database user created for backend access.
- Maintainability: Database tables were created based on the planned database design.
- Testing/Validation: Sample SELECT queries confirmed that the records were inserted successfully.

### S1-T03B - Configure Spring Boot MySQL Database Connection
Status: Completed

Evidence:
- Spring Boot backend successfully connected to MySQL database `logistics_shipment_db`.
- Backend started on port 8080.
- Database connection was verified from Spring Boot startup logs.
- Application database user `logistics_app` is used instead of the root database account.

Practice Mapping:
- Secure SDLC: Backend uses a separate application database user.
- Maintainability: Database configuration is managed through `application.properties`.
- Lightweight DevOps: Configuration change and evidence screenshots are committed to Git.

### S1-T04 - Create Customer Entity, Repository, Service, DTO, Register API and Login API
Status: Completed

Evidence:
- Customer entity created and mapped to the customers database table.
- DTO classes created for registration, login, and authentication response.
- CustomerRepository created using Spring Data JPA.
- CustomerService created to manage registration and login business logic.
- AuthController created with `/api/auth/register` and `/api/auth/login` endpoints.
- Password hashing implemented before storing and comparing passwords.
- Global exception handling added for validation errors, duplicate email registration, and invalid credentials.
- Register API tested successfully.
- Login API tested successfully.
- Invalid password test returned 401 Unauthorized.
- Invalid input test returned 400 Bad Request.

Practice Mapping:
- Agile Scrum: Completed Sprint 1 task S1-T04 based on US-01 and UC-01.
- Secure SDLC: Password hashing, input validation, and controlled error messages were implemented.
- OOP: Customer, DTO, service, repository, controller, utility, and exception classes were separated.
- Maintainability: Backend follows a layered modular monolith structure.
- API Testing: Register, login, invalid password, and validation scenarios were tested.

### S1-T05 - Create Shipment Entity, Repository, Service, DTO and Tracking API

Status: Completed

Evidence:

* Shipment entity created and mapped to the shipments database table.
* ShipmentResponse DTO created to return shipment details safely to the frontend.
* ShipmentRepository created using Spring Data JPA.
* ShipmentService created to handle shipment list and tracking business logic.
* ShipmentController created with customer shipment list and tracking endpoints.
* ResourceNotFoundException added for invalid tracking ID and missing shipment records.
* GlobalExceptionHandler updated to return controlled 404 responses.
* Customer shipment list API tested successfully.
* Valid tracking ID API tested successfully.
* Invalid tracking ID test returned 404 Not Found.
* Access control test confirmed that a customer cannot view another customer’s shipment.

Practice Mapping:

* Agile Scrum: Completed Sprint 1 task S1-T05 based on US-02 and UC-02.
* OOP: Shipment entity, DTO, repository, service, controller, and exception classes were separated.
* Secure SDLC: Customer-specific shipment access was enforced using customerId filtering.
* Maintainability: Shipment tracking logic was implemented in the service layer instead of the controller.
* API Testing: Valid tracking, invalid tracking, shipment list, and access control scenarios were tested.

### S1-T06 - Create Delivery Reschedule API

Status: Completed

Evidence:

* DeliveryReschedule entity created and mapped to the delivery_reschedules database table.
* RescheduleRequest and RescheduleResponse DTOs created.
* DeliveryRescheduleRepository created using Spring Data JPA.
* ShipmentRepository updated to find shipments by shipment ID and customer ID.
* ShipmentService updated to support delivery rescheduling business logic.
* ShipmentController updated with the `/api/shipments/{shipmentId}/reschedule` endpoint.
* The API updates the expected delivery date and changes shipment status to RESCHEDULED.
* A reschedule history record is saved in the delivery_reschedules table.
* Valid reschedule API test completed successfully.
* Tracking API confirmed that the updated delivery date and shipment status were saved.
* Past date validation returned 400 Bad Request.
* Access control test confirmed that a customer cannot reschedule another customer’s shipment.
* Database verification confirmed that reschedule history was stored successfully.

Practice Mapping:

* Agile Scrum: Completed Sprint 1 task S1-T06 based on US-04 and UC-04.
* OOP: DeliveryReschedule entity, request DTO, response DTO, repository, service logic, and controller endpoint were separated.
* Secure SDLC: Customer-specific shipment access and date validation were applied.
* Maintainability: Business rules were implemented in the service layer instead of the controller.
* API Testing: Valid reschedule, past date validation, access control, and database history scenarios were tested.

### S1-T07 - Create Special Delivery Instruction API

Status: Completed

Evidence:

* DeliveryInstruction entity created and mapped to the delivery_instructions database table.
* InstructionRequest and InstructionResponse DTOs created.
* DeliveryInstructionRepository created using Spring Data JPA.
* ShipmentService updated to support special delivery instruction business logic.
* ShipmentController updated with the `/api/shipments/{shipmentId}/instructions` endpoint.
* The API saves delivery instructions against the selected shipment.
* The shipment record is also updated with the latest special instruction.
* Valid delivery instruction API test completed successfully.
* Tracking API confirmed that the updated instruction was displayed in the shipment details.
* Empty instruction validation returned 400 Bad Request.
* Access control test confirmed that a customer cannot update instructions for another customer’s shipment.
* Database verification confirmed that delivery instruction records were stored successfully.

Practice Mapping:

* Agile Scrum: Completed Sprint 1 task S1-T07 based on US-05 and UC-05.
* OOP: DeliveryInstruction entity, request DTO, response DTO, repository, service logic, and controller endpoint were separated.
* Secure SDLC: Customer-specific shipment access and input validation were applied.
* Maintainability: Instruction update logic was implemented in the service layer instead of the controller.
* API Testing: Valid instruction, empty instruction validation, access control, tracking update, and database verification scenarios were tested.

### S1-T08 - Create Shipment Notification API

Status: Completed

Evidence:

* Notification entity created and mapped to the notifications database table.
* NotificationResponse DTO created to return notification details safely to the frontend.
* NotificationRepository created using Spring Data JPA.
* NotificationService created to manage notification retrieval and notification creation.
* NotificationController created with the `/api/notifications/customer/{customerId}` endpoint.
* ShipmentService updated to create notification records after delivery rescheduling.
* ShipmentService updated to create notification records after special delivery instruction updates.
* Customer notification list API tested successfully.
* Instruction update test confirmed that a new notification was created automatically.
* Notification list was re-tested and confirmed that the new instruction notification appeared.
* Invalid customer notification request returned 404 Not Found.
* Database verification confirmed that notification records were stored successfully.

Practice Mapping:

* Agile Scrum: Completed Sprint 1 task S1-T08 based on US-06 and UC-06.
* OOP: Notification entity, response DTO, repository, service, and controller were separated.
* Secure SDLC: Customer-specific notification access was enforced through customerId filtering.
* Maintainability: Notification logic was separated into a dedicated NotificationService.
* API Testing: Notification retrieval, automatic notification creation, invalid customer, and database verification scenarios were tested.

### S1-T09 - Create RouteSphere React Customer Portal User Interface
Status: Completed

Evidence:
- React customer portal UI created using component-based frontend structure.
- UI branding updated to RouteSphere Logistics Management System.
- Authentication page created with login and registration tabs.
- Dashboard page created with summary metrics and customer delivery workspace.
- My Shipments page created to display shipments linked to the logged-in customer.
- Track Shipment page created with shipment search, shipment details, reschedule form, and special delivery instruction form.
- Notifications page created to display customer shipment updates.
- Logout option added to complete the customer session flow.
- Frontend successfully connected to Spring Boot backend APIs.
- Browser testing confirmed that login, dashboard, shipment list, tracking, notifications, and logout worked successfully.

Practice Mapping:
- Agile Scrum: Completed the frontend implementation increment for Sprint 1.
- OOP/Component-Based Design: React UI was separated into reusable components such as AuthPage, Dashboard, ShipmentList, TrackShipment, Notifications, and PortalLayout.
- Maintainability: API calls were separated into api.js instead of being hardcoded inside each component.
- Secure SDLC: Customer-specific data is loaded using the authenticated customer ID.
- Usability: RouteSphere interface was redesigned with a distinct layout, modern navigation, interactive feature buttons, and clear customer workflow.
- Testing/Validation: Frontend screens were manually tested through the browser and screenshots were captured as evidence.
