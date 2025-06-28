package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .map(this::convertToDTO);
    }

    public List<EmployeeDTO> getEmployeesByDepartment(String departmentId) {
        return employeeRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        // Generate new employee ID
        String newId = generateNewEmployeeId();
        employeeDTO.setId(newId);

        // Fetch department
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + employeeDTO.getDepartmentId()));

        Employee employee = convertToEntity(employeeDTO, department);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    public EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        // Update fields
        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setEmail(employeeDTO.getEmail());
        existingEmployee.setPosition(employeeDTO.getPosition());
        existingEmployee.setSalary(employeeDTO.getSalary());

        // Update department if changed
        if (!existingEmployee.getDepartment().getId().equals(employeeDTO.getDepartmentId())) {
            Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + employeeDTO.getDepartmentId()));
            existingEmployee.setDepartment(department);
        }

        Employee savedEmployee = employeeRepository.save(existingEmployee);
        return convertToDTO(savedEmployee);
    }

    public void deleteEmployee(String id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    private String generateNewEmployeeId() {
        Integer maxNumber = employeeRepository.findMaxEmployeeNumber();
        int nextNumber = (maxNumber == null) ? 1 : maxNumber + 1;
        return String.format("emp%03d", nextNumber);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        DepartmentDTO departmentDTO = new DepartmentDTO(
                employee.getDepartment().getId(),
                employee.getDepartment().getName(),
                employee.getDepartment().getLocation()
        );

        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getDepartment().getId(),
                departmentDTO
        );
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO, Department department) {
        return new Employee(
                employeeDTO.getId(),
                employeeDTO.getName(),
                employeeDTO.getEmail(),
                employeeDTO.getPosition(),
                employeeDTO.getSalary(),
                department
        );
    }
}