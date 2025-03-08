import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, RouterOutlet], // ✅ No HttpClientModule here
  template: `
    <h1>Employee Payroll App</h1>
    
    <nav>
      <button routerLink="/list">Home</button> 
      <button routerLink="/add-employee">Add Employee</button> 
    </nav>
    
    <router-outlet></router-outlet> 
  `,
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'Employee Dashboard';
  
  constructor(public router: Router) {}

  navigateToDashboard() {
    this.router.navigate(['/list']);
  } 

  navigateToAddEmployee() {
    this.router.navigate(['/add-employee']);
  }
}
