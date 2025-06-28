package com.example.employeemanagement.controller;

import com.example.employeemanagement.service.DepartmentService;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class SimpleReportController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateSimpleReport() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            
            writer.println("EMPLOYEE MANAGEMENT SYSTEM REPORT");
            writer.println("=================================");
            writer.println();
            
            // Get all employees and departments
            var employees = employeeService.getAllEmployees();
            var departments = departmentService.getAllDepartments();
            
            writer.println("DEPARTMENTS SUMMARY:");
            writer.println("-------------------");
            departments.forEach(dept -> {
                writer.println("Department: " + dept.getName() + " (ID: " + dept.getId() + ")");
                writer.println("Location: " + dept.getLocation());
                
                long empCount = employees.stream()
                    .filter(emp -> emp.getDepartmentId().equals(dept.getId()))
                    .count();
                writer.println("Total Employees: " + empCount);
                writer.println();
            });
            
            writer.println("EMPLOYEES BY DEPARTMENT:");
            writer.println("----------------------");
            
            departments.forEach(dept -> {
                writer.println("DEPARTMENT: " + dept.getName().toUpperCase());
                writer.println("Location: " + dept.getLocation());
                writer.println();
                
                employees.stream()
                    .filter(emp -> emp.getDepartmentId().equals(dept.getId()))
                    .forEach(emp -> {
                        writer.println("  Employee ID: " + emp.getId());
                        writer.println("  Name: " + emp.getName());
                        writer.println("  Email: " + emp.getEmail());
                        writer.println("  Position: " + emp.getPosition());
                        writer.println("  Salary: $" + emp.getSalary());
                        writer.println("  -----------------");
                    });
                writer.println();
            });
            
            writer.println("REPORT STATISTICS:");
            writer.println("-----------------");
            writer.println("Total Departments: " + departments.size());
            writer.println("Total Employees: " + employees.size());
            
            double avgSalary = employees.stream()
                .mapToInt(emp -> emp.getSalary())
                .average()
                .orElse(0.0);
            writer.println("Average Salary: $" + String.format("%.2f", avgSalary));
            
            writer.println();
            writer.println("Report generated on: " + java.time.LocalDateTime.now());
            
            writer.close();
            
            byte[] reportBytes = outputStream.toByteArray();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentDispositionFormData("attachment", "employee-report.txt");
            headers.setContentLength(reportBytes.length);
            
            return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}