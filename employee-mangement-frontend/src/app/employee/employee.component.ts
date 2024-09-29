import {Component} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {Router} from "@angular/router";
import {Employee} from "../Employee";

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [
    FormsModule,
    HttpClientModule
  ],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent {
  employee: Employee = {
    id: '',
    name: '',
    country: ''
  }

  private baseURL = 'http://localhost:8080/api/v1/employees';

  constructor(private http: HttpClient,
              private router: Router) {
    console.log(http)
  }

  saveEmployee(frmModel: NgForm) {
    frmModel.control.markAllAsTouched();
    if (frmModel.invalid) {
      console.log(frmModel);
      alert("Invalid inputs!");
    } else {
      this.http.post(`${this.baseURL}`, this.employee).subscribe({
        next: (res) => {
          alert("Successfully saved !");
          frmModel.resetForm();
        },
        error: (err) => {
          console.error('Employee saving error ', err);
          alert("Failed to save Employee. Please try again");
        }
      });

    }
  }

  AllEmployees() {
    this.router.navigate(['/employee-details'], {
      queryParams: {baseURL: this.baseURL}
    });
  }
}
