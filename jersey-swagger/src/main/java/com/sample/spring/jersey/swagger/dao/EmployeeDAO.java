package com.sample.spring.jersey.swagger.dao;

import com.sample.spring.jersey.swagger.beans.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("employeeDAO")
public class EmployeeDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addEmployee(Employee employee) {
        String query = "INSERT INTO EMPLOYEE (NAME, ROLE) VALUES(?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{ employee.getName(), employee.getRole()};
        int out = jdbcTemplate.update(query, args);
        if (out != 0) {
            System.out.println(String.format("Employee [%s] saved successfully" , employee.toString()));
        }
    }

    public void removeEmployee(Integer employeeId) {
        String query = "DELETE FROM EMPLOYEE WHERE ID = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{employeeId};
        int out = jdbcTemplate.update(query, args);
        if (out != 0) {
            System.out.println("Employee with id : " + employeeId + "deleted successfully");
        }

    }

    public Employee getEmployee(final Integer employeeId) {
        String query = "SELECT ID, NAME, ROLE FROM EMPLOYEE WHERE ID = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{employeeId};
        Employee emp = jdbcTemplate.queryForObject(query, new Object[]{employeeId}, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                Employee employee = new Employee();
                employee.setName(resultSet.getString("NAME"));
                employee.setRole(resultSet.getString("ROLE"));
                return employee;
            }
        });
        return emp;
    }

    public List<Employee> listEmployees() {

        String query = "SELECT * FROM EMPLOYEE";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows =  jdbcTemplate.queryForList(query);
        List<Employee> employees = new ArrayList<>();
        for (Map row:  rows ) {
            Employee employee = new Employee();
            employee.setName((String) row.get("NAME"));
            employee.setRole((String) row.get("ROLE"));
            employees.add(employee);
        }
        return employees;
    }
}
