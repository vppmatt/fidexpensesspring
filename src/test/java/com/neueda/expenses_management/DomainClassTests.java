package com.neueda.expenses_management;

import com.neueda.expenses_management.model.Department;
import com.neueda.expenses_management.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

public class DomainClassTests {

    //create a test method that checks 2 employess with the same data are equal.
    @Test
    public void testThatTwoEmployeesWithTheSameDataAreEqual() {
        Employee emp1 = new Employee(123, "Darren", "Smith", LocalDate.of(2024,11,6),
                Department.HR, 321);

        Employee emp2 = new Employee(123, "Darren", "Smith", LocalDate.of(2024,11,6),
                Department.HR, 321);

        Assertions.assertTrue(emp1.equals(emp2));
        Assertions.assertEquals(emp1, emp2);

    }
}
