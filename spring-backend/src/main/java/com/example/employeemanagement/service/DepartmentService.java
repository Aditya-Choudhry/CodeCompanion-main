package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDepartmentById(String id) {
        return departmentRepository.findById(id)
                .map(this::convertToDTO);
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = convertToEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return convertToDTO(savedDepartment);
    }

    private DepartmentDTO convertToDTO(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getLocation()
        );
    }

    private Department convertToEntity(DepartmentDTO departmentDTO) {
        return new Department(
                departmentDTO.getId(),
                departmentDTO.getName(),
                departmentDTO.getLocation()
        );
    }
}