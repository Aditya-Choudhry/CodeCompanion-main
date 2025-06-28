# How to Run Employee Management System

## Clean Project Structure
Your project now contains only essential components:

```
employee-management-system/
├── spring-backend/          # Java Spring Boot API
├── angular-frontend/        # Angular TypeScript UI  
├── README.md               # Project documentation
└── replit.md               # Technical architecture notes
```

## Running the Complete Application

### Option 1: Run Both Applications (Recommended)

**Terminal 1 - Backend:**
```bash
cd spring-backend
mvn spring-boot:run
```
Backend starts on: http://localhost:8080

**Terminal 2 - Frontend:**
```bash
cd angular-frontend
npm install
ng serve --host 0.0.0.0 --port 4200 --disable-host-check
```
Frontend starts on: http://localhost:4200

### Option 2: Backend Only (API Testing)
```bash
cd spring-backend
mvn spring-boot:run
```
Access API directly at: http://localhost:8080/api/employees

## What's Available

### Backend Features
- REST API with employee CRUD operations
- H2 in-memory database with sample data
- Spring Boot with JPA/Hibernate
- CORS enabled for frontend communication

### Frontend Features  
- Angular 19 with Bootstrap 5 styling
- Employee management interface
- Department filtering
- Responsive design

### Access Points
- **Frontend UI**: http://localhost:4200
- **Backend API**: http://localhost:8080/api/employees
- **Database Console**: http://localhost:8080/h2-console

## Current Status
✅ All unnecessary Replit/Node.js files removed
✅ Clean Spring Boot + Angular architecture  
✅ Ready for local development or deployment