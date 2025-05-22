package com.neueda.expenses_management.control;

import com.neueda.expenses_management.exceptions.EmployeeNotFoundException;
import com.neueda.expenses_management.model.Employee;
import com.neueda.expenses_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //eg /api/employee?cc=101
    @GetMapping()
    public List<Employee> getAllEmployees(@RequestParam("cc") Optional<Integer> cc) {
        System.out.println("cc was " + (cc.isPresent() ? cc.get() : "null"));
        return employeeService.getEmployees();
    }

    @PostMapping()
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee e) {
        Employee newEmployee = employeeService.addEmployee(e);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") Integer employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployee(employeeId);
    }
}
