package com.sample.spring.jdbc.dao;

import com.sample.spring.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository("emlpoyeeDAO")
public class JDBCEmployeeDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addEmployee(Employee employee) {
        String query = "INSERT INTO EMPLOYEE (ID, NAME, ROLE) VALUES(?,?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{employee.getId(), employee.getName(), employee.getRole()};
        int out = jdbcTemplate.update(query, args);
        if (out != 0) {
            System.out.println("Employee saved with id : " + employee.getId());
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
                employee.setId(resultSet.getInt("id"));
                return employee;
            }
        });
        return emp;
    }

    public  List<Employee> listEmployees() {

        String query = "SELECT * FROM EMPLOYEE";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> rows =  jdbcTemplate.queryForList(query);
        List<Employee> employees = new ArrayList<Employee>();
        for (Map row:  rows ) {
            Employee employee = new Employee();
            Long id =  (Long)row.get("ID");
            employee.setId(id.intValue());
            employee.setName((String) row.get("NAME"));
            employee.setRole((String) row.get("ROLE"));
            employees.add(employee);
        }
        return employees;
    }
}