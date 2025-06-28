import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { EmployeeService } from '../../services/employee.service';
import { Employee, Department, CreateEmployeeRequest } from '../../models/employee.model';

@Component({
  selector: 'app-employee-modal',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './employee-modal.component.html',
  styleUrls: ['./employee-modal.component.scss']
})
export class EmployeeModalComponent implements OnInit {
  @Input() employee: Employee | null = null;
  @Input() departments: Department[] = [];
  @Input() isEditMode: boolean = false;
  @Output() close = new EventEmitter<void>();
  @Output() employeeSaved = new EventEmitter<void>();

  employeeForm: FormGroup;
  isSubmitting: boolean = false;

  constructor(
    private fb: FormBuilder,
    private employeeService: EmployeeService
  ) {
    this.employeeForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      position: ['', [Validators.required, Validators.minLength(2)]],
      salary: ['', [Validators.required, Validators.min(0)]],
      departmentId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    if (this.employee && this.isEditMode) {
      this.employeeForm.patchValue({
        name: this.employee.name,
        email: this.employee.email,
        position: this.employee.position,
        salary: this.employee.salary,
        departmentId: this.employee.departmentId
      });
    }
  }

  get isViewMode(): boolean {
    return !this.isEditMode && this.employee !== null;
  }

  get isCreateMode(): boolean {
    return !this.isEditMode && this.employee === null;
  }

  get modalTitle(): string {
    if (this.isViewMode) return 'Employee Details';
    if (this.isEditMode) return 'Edit Employee';
    return 'Add New Employee';
  }

  onSubmit(): void {
    if (this.employeeForm.valid && !this.isViewMode) {
      this.isSubmitting = true;
      const formData: CreateEmployeeRequest = this.employeeForm.value;

      const operation = this.isEditMode && this.employee
        ? this.employeeService.updateEmployee(this.employee.id, formData)
        : this.employeeService.createEmployee(formData);

      operation.subscribe({
        next: () => {
          this.isSubmitting = false;
          this.employeeSaved.emit();
          alert(this.isEditMode ? 'Employee updated successfully!' : 'Employee created successfully!');
        },
        error: (error) => {
          console.error('Error saving employee:', error);
          this.isSubmitting = false;
          alert('Error saving employee!');
        }
      });
    }
  }

  onClose(): void {
    this.close.emit();
  }

  getDepartmentName(departmentId: string): string {
    const dept = this.departments.find(d => d.id === departmentId);
    return dept ? dept.name : 'Unknown';
  }

  // Form validation helpers
  isFieldInvalid(fieldName: string): boolean {
    const field = this.employeeForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  getFieldError(fieldName: string): string {
    const field = this.employeeForm.get(fieldName);
    if (field?.errors) {
      if (field.errors['required']) return `${fieldName} is required`;
      if (field.errors['email']) return 'Invalid email address';
      if (field.errors['minlength']) return `${fieldName} is too short`;
      if (field.errors['min']) return `${fieldName} must be positive`;
    }
    return '';
  }
}