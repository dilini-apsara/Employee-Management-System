import {Component, OnInit} from '@angular/core';
import {FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {Employee} from "../Employee";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-employee',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  templateUrl: './update-employee.component.html',
  styleUrl: './update-employee.component.css'
})
export class UpdateEmployeeComponent implements OnInit {

  employee: Employee = {
    id: '',
    name: '',
    country: ''
  };
  baseURL: string | null = null;
  empId: null | string = null;

  constructor(private http: HttpClient,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.employee.id = params['id'];
      this.baseURL = params['baseURL'];
    })
    console.log(this.empId)
    console.log(this.employee.id)
    if (this.employee.id) {
      this.getEmployeeDetails(this.employee.id);
    }

  }

  getEmployeeDetails(id: String) {
    this.http.get<Employee>(`${this.baseURL}/${id}`).subscribe({
      next: (res) => {
        this.employee = res;
      },
      error: (err) => {
        console.log('Error ', err);
      }
    })
  }

  updateEmployee(frmModel: NgForm) {
    if (frmModel.valid) {
      this.http.put(`${this.baseURL}/${this.employee.id}`, this.employee).subscribe({
          next: (res) => {
            alert('Employee details updated successfully!');
            this.router.navigate(['']);
          },
          error: (err) => {
            console.log('Error updating  ', err);
            alert("Update failed, please try again !");
          }
        }
      )
    }
  }
}
