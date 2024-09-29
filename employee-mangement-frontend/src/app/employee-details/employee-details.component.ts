import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Employee} from "../Employee";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-employee-details',
  standalone: true,
  imports: [CommonModule,HttpClientModule],
  templateUrl: './employee-details.component.html',
  styleUrl: './employee-details.component.css'
})
export class EmployeeDetailsComponent implements OnInit {
  baseURL: string | null = null;
  employees: Employee[] = [];

  constructor(private route: ActivatedRoute,
              private router:Router,
              private http:HttpClient) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.baseURL = params['baseURL'];
      console.log(this.baseURL);
      this.getAllEmployees();
    });
  }

  getAllEmployees() {
    this.http.get<Employee[]>(`${this.baseURL}`).subscribe({
      next:(res)=>{
        this.employees=res;
      },error:(err)=>{
        console.log('Error ',err);
      }
      }
    );
  }


  deleteEmployee(id:string) {
    this.http.delete(`${this.baseURL}/${id}`).subscribe({
      next:()=>{
        alert("Employee deleted successfully!");
        this.getAllEmployees();
      },
      error:(err)=>{
        console.log('Error ',err);
      }
    });
  }

  updateEmployee(empId:String) {
    console.log(empId)
    this.router.navigate(['/update-details'],{
      queryParams:{baseURL:this.baseURL,id:empId}
    });
  }
}
