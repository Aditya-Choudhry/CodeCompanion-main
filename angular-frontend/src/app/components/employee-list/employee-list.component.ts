import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EmployeeService } from '../../services/employee.service';
import { Employee, Department } from '../../models/employee.model';
import { EmployeeModalComponent } from '../employee-modal/employee-modal.component';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, EmployeeModalComponent],
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[] = [];
  departments: Department[] = [];
  filteredEmployees: Employee[] = [];
  searchDepartmentId: string = '';
  selectedEmployee: Employee | null = null;
  isLoading: boolean = false;
  showEmployeeModal: boolean = false;
  isEditMode: boolean = false;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.loadEmployees();
    this.loadDepartments();
  }

  loadEmployees(): void {
    this.isLoading = true;
    this.employeeService.getAllEmployees().subscribe({
      next: (employees) => {
        this.employees = employees;
        this.filteredEmployees = employees;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading employees:', error);
        this.isLoading = false;
      }
    });
  }

  loadDepartments(): void {
    this.employeeService.getAllDepartments().subscribe({
      next: (departments) => {
        this.departments = departments;
      },
      error: (error) => {
        console.error('Error loading departments:', error);
      }
    });
  }

  searchByDepartment(): void {
    if (!this.searchDepartmentId.trim()) {
      this.filteredEmployees = this.employees;
      return;
    }

    this.isLoading = true;
    this.employeeService.getEmployeesByDepartment(this.searchDepartmentId).subscribe({
      next: (employees) => {
        this.filteredEmployees = employees;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error searching employees:', error);
        this.filteredEmployees = [];
        this.isLoading = false;
      }
    });
  }

  showAllEmployees(): void {
    this.searchDepartmentId = '';
    this.filteredEmployees = this.employees;
  }

  viewEmployee(employee: Employee): void {
    this.selectedEmployee = employee;
    this.showEmployeeModal = true;
    this.isEditMode = false;
  }

  editEmployee(employee: Employee): void {
    this.selectedEmployee = employee;
    this.showEmployeeModal = true;
    this.isEditMode = true;
  }

  deleteEmployee(employee: Employee): void {
    if (confirm(`Are you sure you want to delete ${employee.name}?`)) {
      this.employeeService.deleteEmployee(employee.id).subscribe({
        next: () => {
          this.loadEmployees();
          alert('Employee deleted successfully!');
        },
        error: (error) => {
          console.error('Error deleting employee:', error);
          alert('Error deleting employee!');
        }
      });
    }
  }

  addNewEmployee(): void {
    this.selectedEmployee = null;
    this.showEmployeeModal = true;
    this.isEditMode = false;
  }

  closeModal(): void {
    this.showEmployeeModal = false;
    this.selectedEmployee = null;
  }

  onEmployeeSaved(): void {
    this.closeModal();
    this.loadEmployees();
  }

  generateReport(): void {
    this.employeeService.generateReport().subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'employees-report.pdf';
        link.click();
        window.URL.revokeObjectURL(url);
      },
      error: (error) => {
        console.error('Error generating report:', error);
        alert('Error generating report!');
      }
    });
  }

  getDepartmentName(departmentId: string): string {
    const dept = this.departments.find(d => d.id === departmentId);
    return dept ? dept.name : 'Unknown';
  }

  trackByEmployeeId(index: number, employee: Employee): string {
    return employee.id;
  }

  getAverageSalary(): number {
    if (this.filteredEmployees.length === 0) return 0;
    const total = this.filteredEmployees.reduce((sum, emp) => sum + emp.salary, 0);
    return total / this.filteredEmployees.length;
  }
}