package lk.project.employee_details.service;

import lk.project.employee_details.to.EmployeeTo;
import lk.project.employee_details.to.request.EmployeeReqTo;

import java.util.List;


public interface EmployeeService {
   EmployeeTo saveEmployee(EmployeeReqTo employeeReqTo);

   void updateEmployee(EmployeeReqTo employeeReqTo);

   void deleteEmployee(String id);

   EmployeeTo getEmployeeById(String id);

   List<EmployeeTo> getAllEmployees();
}
