package com.neueda.expenses_management.data;

import com.neueda.expenses_management.model.Department;
import com.neueda.expenses_management.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        Date dateJoined = rs.getDate("dateJoined");
        String department = rs.getString("department");
        Integer costCenter = rs.getInt("costCenter");

        return new Employee(id, firstname, lastname, dateJoined.toLocalDate(),
                Department.valueOf(department), costCenter);
    }

}
