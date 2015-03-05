package com.sample.spring.service;

import com.sample.spring.domain.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by niyuj on 16/02/2015.
 */
public interface EmployeeService {

    Employee getEmployee(Integer employeeId);

    void removeEmployee(Integer employeeId);

    void addEmployee(Employee employee);

    void addEmployees(Map<Integer, Employee> employees);

    List<Employee> listEmployees();

}
