package com.example.employeemanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeDTO {
    private String id;

    @NotBlank(message = "Employee name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Position is required")
    private String position;

    @Min(value = 0, message = "Salary must be positive")
    @NotNull(message = "Salary is required")
    private Integer salary;

    @NotBlank(message = "Department ID is required")
    private String departmentId;

    // Department info for response
    private DepartmentDTO department;

    // Default constructor
    public EmployeeDTO() {}

    // Constructor for requests (without department info)
    public EmployeeDTO(String name, String email, String position, Integer salary, String departmentId) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    // Constructor for responses (with department info)
    public EmployeeDTO(String id, String name, String email, String position, Integer salary, String departmentId, DepartmentDTO department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.departmentId = departmentId;
        this.department = department;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }
}