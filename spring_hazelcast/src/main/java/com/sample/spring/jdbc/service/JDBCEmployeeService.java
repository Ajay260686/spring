package com.sample.spring.jdbc.service;

import com.sample.spring.domain.Employee;
import com.sample.spring.jdbc.dao.JDBCEmployeeDAO;
import com.sample.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("JDBCEmployeeService")
public class JDBCEmployeeService implements EmployeeService {

    @Autowired
    JDBCEmployeeDAO employeeDAO;

    @Override
    public Employee getEmployee(Integer employeeId) {
        return employeeDAO.getEmployee(employeeId);
    }

    @Override
    public void removeEmployee(Integer employeeId) {
        employeeDAO.removeEmployee(employeeId);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    @Override
    public void addEmployees(Map<Integer, Employee> employees) {
        for(Employee employee : employees.values()){
            employeeDAO.addEmployee(employee);
        }
    }

    @Override
    public List<Employee> listEmployees() {
        return employeeDAO.listEmployees();
    }
}
