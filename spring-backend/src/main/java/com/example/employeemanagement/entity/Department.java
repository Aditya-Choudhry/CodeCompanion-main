package com.example.employeemanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    private String id;

    @NotBlank(message = "Department name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;

    // Default constructor
    public Department() {}

    // Constructor
    public Department(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}