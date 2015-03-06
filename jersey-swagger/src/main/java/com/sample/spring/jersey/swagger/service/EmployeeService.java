package com.sample.spring.jersey.swagger.service;

import com.sample.spring.jersey.swagger.beans.Employee;
import com.sample.spring.jersey.swagger.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public List<Employee> getAllEmployees(){
        return employeeDAO.listEmployees();
    }

    public Employee getEmployee(Integer employeeId) {
        return employeeDAO.getEmployee(employeeId);
    }

    public void removeEmployee(Integer employeeId) {
        employeeDAO.removeEmployee(employeeId);
    }

    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }
}
