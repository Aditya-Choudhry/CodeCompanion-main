package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    
    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") String departmentId);

    @Query("SELECT MAX(CAST(SUBSTRING(e.id, 4) AS int)) FROM Employee e WHERE e.id LIKE 'emp%'")
    Integer findMaxEmployeeNumber();
}