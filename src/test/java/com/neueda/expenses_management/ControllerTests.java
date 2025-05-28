package com.neueda.expenses_management;

import com.neueda.expenses_management.control.EmployeeController;
import com.neueda.expenses_management.data.EmployeeDao;
import com.neueda.expenses_management.model.Department;
import com.neueda.expenses_management.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    EmployeeDao employeeDao;

    @BeforeEach
    public void setupDataMocks() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"bob","", LocalDate.now(), Department.HR, 0));
        employees.add(new Employee(2,"jo","", LocalDate.now(), Department.HR, 0));
        Mockito.when(employeeDao.getAllEmployees()).thenReturn(employees);
    }

    @Test
    public void testThatGetAllRespondsWithStatus200() throws Exception {
        mockMvc.perform(get("/api/employee"))
                .andExpect(status().is(200));
    }

}
