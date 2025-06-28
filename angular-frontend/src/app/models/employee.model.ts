export interface Department {
  id: string;
  name: string;
  location: string;
}

export interface Employee {
  id: string;
  name: string;
  email: string;
  position: string;
  salary: number;
  departmentId: string;
  department: Department;
}

export interface CreateEmployeeRequest {
  name: string;
  email: string;
  position: string;
  salary: number;
  departmentId: string;
}