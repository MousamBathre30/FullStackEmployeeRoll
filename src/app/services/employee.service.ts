import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'  // ✅ Ensures it's available globally
})
export class EmployeeService {
  private baseUrl = 'http://localhost:8080/employees';  // ✅ Matches Spring Boot controller

  constructor(private http: HttpClient) {}  // ✅ Inject HttpClient

  // ✅ Fetch all employees
  getEmployeePayrollData(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  // ✅ Fetch employee by ID
  getEmployeeById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/get/${id}`);
  }

  // ✅ Create a new employee
  createEmployeePayrollData(employee: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, employee);
  }

  // ✅ Update employee data
  updateEmployee(id: number, employee: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/${id}`, employee);
  }

  // ✅ Fetch employees by department
  getEmployeesByDepartment(department: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/department/${department}`);
  }

  // ✅ Delete an employee
  deleteEmployee(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`);
  }
}
