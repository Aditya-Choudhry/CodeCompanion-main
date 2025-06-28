
# Employee Management System

A full-stack Employee Management System built with **Spring Boot** backend and **Angular** frontend with Bootstrap 5 styling.

## Project Structure

```
├── spring-backend/          # Spring Boot REST API
├── angular-frontend/        # Angular user interface with Bootstrap
├── package.json            # Root project configuration
└── README.md               # This file
```

## Technologies

### Backend (Spring Boot)
- Java 21
- Spring Boot 3.2
- Spring Data JPA
- H2 Database (in-memory)
- Maven

### Frontend (Angular)
- Angular 19
- TypeScript
- Bootstrap 5.3
- Font Awesome Icons
- RxJS

## How to Run on Replit

### Quick Start (Recommended)
1. **Click the Run button** - This will start both frontend and backend automatically
   - Spring Boot backend runs on port 8080
   - Angular frontend runs on port 4200
   
2. **Access the application**:
   - **Frontend UI**: Open the webview (automatically opens)
   - **Backend API**: `https://your-repl-name.your-username.repl.co:8080/api/employees`
   - **H2 Database Console**: `https://your-repl-name.your-username.repl.co:8080/h2-console`

### Manual Setup

#### Prerequisites
- Java 21+ (pre-installed on Replit)
- Node.js 20+ (pre-installed on Replit)
- Maven 3.6+ (pre-installed on Replit)

#### Option 1: Run Both Applications Together
```bash
# Click the Run button or use terminal:
npm run dev  # Starts Spring Boot backend
```

In a new terminal:
```bash
npm run frontend  # Starts Angular frontend
```

#### Option 2: Run Applications Separately

**Backend Only:**
```bash
cd spring-backend
mvn spring-boot:run
```
Backend runs on: `https://your-repl-name.your-username.repl.co:8080`

**Frontend Only:**
```bash
cd angular-frontend
npm install
ng serve --host 0.0.0.0 --port 4200 --disable-host-check
```
Frontend runs on: `https://your-repl-name.your-username.repl.co:4200`

#### Option 3: Use Bootstrap HTML (No Angular CLI needed)
```bash
# Start only the backend
cd spring-backend
mvn spring-boot:run
```
Then open `angular-frontend/index.html` directly in the browser - it's a complete Bootstrap frontend that works with the Spring Boot API.

## Features

- **Employee Management**: View, add, edit, delete employees
- **Department Filtering**: Search employees by department ID
- **Responsive Design**: Bootstrap 5 responsive UI with mobile support
- **PDF Reports**: Generate and download employee reports
- **Statistics Dashboard**: Employee count and department statistics
- **Real-time Data**: Live updates with Spring Boot backend
- **Database Console**: H2 console for database management

## API Endpoints

Base URL: `https://your-repl-name.your-username.repl.co:8080/api`

- `GET /employees` - List all employees
- `GET /departments` - List all departments
- `GET /employees/{id}` - Get single employee
- `POST /employees` - Create employee
- `PUT /employees/{id}` - Update employee
- `DELETE /employees/{id}` - Delete employee
- `GET /employees/report` - Generate PDF report

## Database Access

The application uses H2 in-memory database with sample data:
- **H2 Console**: `https://your-repl-name.your-username.repl.co:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:employeedb`
- **Username**: `sa`
- **Password**: (empty)

## Development Notes

- **Hot Reload**: Both frontend and backend support hot reload
- **CORS**: Configured for cross-origin requests between frontend and backend
- **Sample Data**: Database is pre-populated with sample employees and departments
- **Bootstrap Components**: Modal dialogs, responsive tables, and form validation
- **Error Handling**: Comprehensive error handling on both frontend and backend

## Troubleshooting

1. **If frontend doesn't load**: Make sure both backend and frontend are running
2. **API errors**: Check that Spring Boot backend is running on port 8080
3. **Database issues**: H2 database resets on each restart (in-memory)
4. **Port conflicts**: Frontend uses 4200, backend uses 8080
