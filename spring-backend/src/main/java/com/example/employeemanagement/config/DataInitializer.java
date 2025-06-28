package com.example.employeemanagement.config;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (departmentRepository.count() > 0) {
            System.out.println("Data already exists, skipping initialization.");
            return;
        }

        // Initialize departments
        Department dept1 = new Department("dept01", "Human Resources", "Building A");
        Department dept2 = new Department("dept02", "Engineering", "Building B");

        departmentRepository.save(dept1);
        departmentRepository.save(dept2);

        // Initialize employees
        Employee emp1 = new Employee("emp001", "Alice Smith", "alice.smith@example.com", "Recruiter", 60000, dept1);
        Employee emp2 = new Employee("emp002", "Bob Johnson", "bob.johnson@example.com", "Software Engineer", 80000, dept2);
        Employee emp3 = new Employee("emp003", "Charlie Brown", "charlie.brown@example.com", "HR Assistant", 40000, dept1);
        Employee emp4 = new Employee("emp004", "Diana Prince", "diana.prince@example.com", "System Architect", 90000, dept2);

        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);
        employeeRepository.save(emp4);

        System.out.println("Sample data initialized successfully!");
    }
}