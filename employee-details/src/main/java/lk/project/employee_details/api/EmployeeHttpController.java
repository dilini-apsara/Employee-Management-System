package lk.project.employee_details.api;

import lk.project.employee_details.service.EmployeeService;
import lk.project.employee_details.to.EmployeeTo;
import lk.project.employee_details.to.request.EmployeeReqTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin
public class EmployeeHttpController {

    @Autowired
    private EmployeeService employeeService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public EmployeeTo creteEmployee(@RequestBody @Validated EmployeeReqTo employeeReqTo) {
        return employeeService.saveEmployee(employeeReqTo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = "application/json")
    public void updateEmployee(@PathVariable String id, @RequestBody @Validated EmployeeReqTo employeeReqTo) {
       employeeReqTo.setId(id);
        employeeService.updateEmployee(employeeReqTo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}",produces = "application/json")
    public EmployeeTo getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = {"application/json"})
    public List<EmployeeTo> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
