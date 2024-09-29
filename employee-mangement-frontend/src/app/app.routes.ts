import { Routes } from '@angular/router';

import {EmployeeComponent} from "./employee/employee.component";
import {EmployeeDetailsComponent} from "./employee-details/employee-details.component";
import {UpdateEmployeeComponent} from "./update-employee/update-employee.component";

export const routes: Routes = [
  {
    path:'',
    component:EmployeeComponent
  },
  {
    path:'employee-details',
    component:EmployeeDetailsComponent
  },
  {
    path:'update-details',
    component:UpdateEmployeeComponent
  }
];
