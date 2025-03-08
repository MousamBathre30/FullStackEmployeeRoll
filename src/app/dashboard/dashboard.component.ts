import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  imports: [CommonModule, RouterModule],
  encapsulation: ViewEncapsulation.None,
})  
export class DashboardComponent implements OnInit {
  employees: any[] = [];

  constructor(private employeeService: EmployeeService, private router: Router) {}

  ngOnInit(): void {
    this.fetchEmployees();
  }

  fetchEmployees() {
    this.employeeService.getEmployeePayrollData().subscribe({
      next: (data) => {
        this.employees = data.data;
      },
      error: (error) => {
        console.error('Error fetching employees:', error);
      }
    });
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe(() => {
      alert('Employee Deleted');
      this.fetchEmployees();
    });
  }

  navigateToAddPerson() {
    this.router.navigate(['/add-employee']);
  }
}
