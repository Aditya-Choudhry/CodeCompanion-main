import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    EmployeeListComponent
  ],
  template: `
    <div class="container-fluid">
      <nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
        <div class="container">
          <a class="navbar-brand" href="#">
            <i class="fas fa-users me-2"></i>
            Employee Management System
          </a>
          <div class="navbar-nav ms-auto">
            <span class="navbar-text">
              Spring Boot + Angular Implementation
            </span>
          </div>
        </div>
      </nav>
      <app-employee-list></app-employee-list>
    </div>
  `,
  styles: [`
    .navbar-brand {
      font-weight: 600;
      font-size: 1.5rem;
    }
    
    .container-fluid {
      padding: 0;
    }
    
    .navbar-text {
      color: rgba(255,255,255,0.8) !important;
    }
  `]
})
export class AppComponent {
  title = 'Employee Management System';
}