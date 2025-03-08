import { Component, ViewEncapsulation } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { EmployeePayrollDTO } from '../dto/employee.service.dto';
import { EmployeeService } from '../services/employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule], // ✅ Corrected syntax
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class EmployeeFormComponent {
  employee: EmployeePayrollDTO = {
    name: '',
    salary: '',
    gender: '',
    startDate: '',
    note: '',
    profilepic: '',
    departments: [],
  };

  departments: string[] = ['Sales', 'HR', 'Finance', 'Engineering', 'Marketing'];

  constructor(public employeeService: EmployeeService, private router: Router) {}

  onSubmit() {
    this.employeeService.createEmployeePayrollData(this.employee).subscribe(() => {
      this.router.navigate(['/list']); // ✅ Redirect back to dashboard
    });
  }

  updateDepartments(event: any) {
    if (event.target.checked) {
      this.employee.departments.push(event.target.value);
    } else {
      this.employee.departments = this.employee.departments.filter(dept => dept !== event.target.value);
    }
  }
}
