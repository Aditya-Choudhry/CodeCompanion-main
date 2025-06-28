import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee, Department, CreateEmployeeRequest } from '../models/employee.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiUrl = 'http://localhost:5000/api';

  constructor(private http: HttpClient) { }

  // Employee operations
  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiUrl}/employees`);
  }

  getEmployee(id: string): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}/employees/${id}`);
  }

  createEmployee(employee: CreateEmployeeRequest): Observable<Employee> {
    return this.http.post<Employee>(`${this.apiUrl}/employees`, employee);
  }

  updateEmployee(id: string, employee: CreateEmployeeRequest): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiUrl}/employees/${id}`, employee);
  }

  deleteEmployee(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/employees/${id}`);
  }

  // Department operations
  getAllDepartments(): Observable<Department[]> {
    return this.http.get<Department[]>(`${this.apiUrl}/departments`);
  }

  getEmployeesByDepartment(departmentId: string): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiUrl}/departments/${departmentId}/employees`);
  }

  // Report generation
  generateReport(): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/employees/report`, { 
      responseType: 'blob' 
    });
  }
}