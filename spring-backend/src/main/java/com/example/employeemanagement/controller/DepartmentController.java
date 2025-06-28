package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.service.DepartmentService;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable String id) {
        return departmentService.getDepartmentById(id)
                .map(department -> ResponseEntity.ok(department))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@PathVariable String departmentId) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(departmentId);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        return ResponseEntity.ok(createdDepartment);
    }
}