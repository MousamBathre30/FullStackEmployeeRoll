import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EmployeeFormComponent } from './employee-form/employee-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' }, // ✅ Fixed redirect
  { path: 'list', component: DashboardComponent },
  { path: 'add-employee', component: EmployeeFormComponent }, // ✅ Fixed path naming
];
