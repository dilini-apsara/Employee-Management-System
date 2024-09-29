package lk.project.employee_details;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lk.project.employee_details.entity.Employee;
import lk.project.employee_details.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

class EmployeeDetailsApplicationTests {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    void contextLoads() {
        assertNotNull(mapper, " should not be null");
    }

    @Test
    void save(){
       Employee employee1= new Employee("E010","Kasun Sampath","UK");
        Employee saved = employeeRepository.save(employee1);

        Employee employee = em.find(Employee.class, saved.getId());
        assertNotNull(employee, "Saved employee should not be null");
        assertEquals(saved.getId(), employee.getId());
        assertEquals("Kasun Sampath", employee.getName());
        assertEquals("Sri Lanka", employee.getCountry());

    }






}
