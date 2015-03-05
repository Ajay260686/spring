package com.sample.spring.hazelcast.dao;

import com.hazelcast.core.MapStore;
import com.sample.spring.domain.Employee;
import com.sample.spring.jdbc.dao.JDBCEmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class HazelcastEmployeeDAO implements MapStore<Integer,Employee>{

    @Autowired
    private  JDBCEmployeeDAO employeeDAO;

    @Override
    public void store(Integer integer, Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    @Override
    public void storeAll(Map<Integer, Employee> map) {

        for (Map.Entry<Integer, Employee> mapEntry : map.entrySet()) {
            store(mapEntry.getKey(), mapEntry.getValue());
        }
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void deleteAll(Collection<Integer> collection) {

    }

    @Override
    public Employee load(Integer employeeId) {
        return employeeDAO.getEmployee(employeeId);
    }

    @Override
    public Map<Integer, Employee> loadAll(Collection<Integer> collection) {
        Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
        List<Employee> employees = employeeDAO.listEmployees();
        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }
        return employeeMap;
    }

    @Override
    public Set<Integer> loadAllKeys() {
        Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
        List<Employee> employees = employeeDAO.listEmployees();
        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }
        return employeeMap.keySet();
    }
}
