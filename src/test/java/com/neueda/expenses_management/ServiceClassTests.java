package com.neueda.expenses_management;

import com.neueda.expenses_management.data.EmployeeDao;
import com.neueda.expenses_management.model.Department;
import com.neueda.expenses_management.model.Employee;
import com.neueda.expenses_management.service.EmployeeService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ServiceClassTests {

    @Autowired
    EmployeeService employeeService;

    @MockitoBean
    EmployeeDao employeeDao;

    @BeforeEach
    public void setupDataMocks() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"bob","", LocalDate.now(), Department.HR, 0));
        employees.add(new Employee(2,"jo","", LocalDate.now(), Department.HR, 0));
        Mockito.when(employeeDao.getAllEmployees()).thenReturn(employees);
    }

    //create a test that the getEmployees method returns the expected number of records
    @Test
    public void testGetEmployeesReturnsAllRecords() {
        List<Employee> employees = employeeService.getEmployees();
        assertEquals(2, employees.size());
    }

    @Test
    public void testFirstNamesAreCapitalizedWhenCallingGet() {
        List<Employee> employees = employeeService.getEmployees();
        assertEquals("BOB", employees.get(0).getFirstname());
    }
}
