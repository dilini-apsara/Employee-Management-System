package lk.project.employee_details.service.impl;

import lk.project.employee_details.entity.Employee;
import lk.project.employee_details.repository.EmployeeRepository;
import lk.project.employee_details.service.EmployeeService;
import lk.project.employee_details.to.EmployeeTo;
import lk.project.employee_details.to.request.EmployeeReqTo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;

    }

    @Override
    public EmployeeTo saveEmployee(EmployeeReqTo employeeReqTo) {
        
        Optional<Employee> isExist = employeeRepository.findById(employeeReqTo.getId());
        if ( isExist.isPresent()) throw new RuntimeException("Employee Is already exist in system");

        Employee employee = mapper.map(employeeReqTo, Employee.class);
        employeeRepository.save(employee);
        return mapper.map(employee, EmployeeTo.class);

    }

    @Override
    public void updateEmployee(EmployeeReqTo employeeReqTo) {
        Employee currentEmployee = employeeRepository.findById(employeeReqTo.getId()).orElseThrow(() -> new RuntimeException("No Employee was found"));

        if (employeeReqTo.getName() != null && !employeeReqTo.getName().isEmpty()) {
            currentEmployee.setName(employeeReqTo.getName());
        }

        if (employeeReqTo.getCountry() != null && !employeeReqTo.getCountry().isEmpty()) {
            currentEmployee.setCountry(employeeReqTo.getCountry());
        }

        employeeRepository.save(currentEmployee);

    }

    @Override
    public void deleteEmployee(String id) {
        if (!employeeRepository.existsById(id)) throw new RuntimeException("No employee found");
        employeeRepository.deleteById(id);

    }

    @Override
    public EmployeeTo getEmployeeById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) throw new RuntimeException("No employee found");
        EmployeeTo employeeTo = mapper.map(employee.get(), EmployeeTo.class);
        return employeeTo;
    }

    @Override
    public List<EmployeeTo> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(emp -> {
            EmployeeTo employeeTo = mapper.map(emp, EmployeeTo.class);
            return employeeTo;
        }).collect(Collectors.toList());

    }
}
