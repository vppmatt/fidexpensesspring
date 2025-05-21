package com.neueda.expenses_management.service;

import com.neueda.expenses_management.data.EmployeeDao;
import com.neueda.expenses_management.exceptions.EmployeeNotFoundException;
import com.neueda.expenses_management.model.Department;
import com.neueda.expenses_management.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public List<Employee> getEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Employee addEmployee(Employee newEmployee) {
        return employeeDao.save(newEmployee);
    }


    public Employee getEmployee(Integer employeeId) throws EmployeeNotFoundException {
        return employeeDao.getById(employeeId);
    }
}
