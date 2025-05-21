package com.neueda.expenses_management.data;

import com.neueda.expenses_management.exceptions.EmployeeNotFoundException;
import com.neueda.expenses_management.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class EmployeeDao extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void init() {
        super.setDataSource(dataSource);
    }

    public List<Employee> getAllEmployees() {
        JdbcTemplate jdbcTemplate = super.getJdbcTemplate();
        return jdbcTemplate.query("select * from employees", new EmployeeRowMapper());
    }

    public Employee getById(Integer id) throws EmployeeNotFoundException {
        JdbcTemplate jdbcTemplate = super.getJdbcTemplate();
        String sql = "select * from employees where id = ?";
        Object[] parameters = {id};
        try {
            return jdbcTemplate.queryForObject(sql, parameters, new EmployeeRowMapper());
        }
        catch (Exception e) {
            throw new EmployeeNotFoundException("No employee found with id " + id);
        }
    }

    public Employee save(Employee employee) {
        JdbcTemplate jdbcTemplate = super.getJdbcTemplate();

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("employees")
                .usingGeneratedKeyColumns("id");

        HashMap<String, Object> params = new HashMap<>();
        params.put("firstname", employee.getFirstname());
        params.put("lastname", employee.getLastname());
        params.put("dateJoined", employee.getDateJoined());
        params.put("department", employee.getDepartment().name());
        params.put("costCenter", employee.getCostCenter());

        Integer id = simpleJdbcInsert.executeAndReturnKey(params).intValue();
        employee.setId(id);
        return employee;
    }

}
