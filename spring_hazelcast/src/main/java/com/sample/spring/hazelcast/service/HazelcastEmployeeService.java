package com.sample.spring.hazelcast.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.sample.spring.domain.Employee;
import com.sample.spring.jdbc.dao.JDBCEmployeeDAO;
import com.sample.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service("HazelcastEmployeeService")
public class HazelcastEmployeeService implements EmployeeService {

    @Autowired
    private HazelcastInstance instance;

    @Autowired
    private JDBCEmployeeDAO employeeDAO;

    @Override
    public Employee getEmployee(Integer employeeId) {
        IMap<Integer , Employee> dataStore = instance.getMap("employeeMap");
        return dataStore.get(employeeId);
    }

    @Override
    public void removeEmployee(Integer employeeId) {
        IMap<Integer , Employee> dataStore = instance.getMap("employeeMap");
        dataStore.remove(employeeId);
    }

    @Override
    public void addEmployee(Employee employee) {

        IMap<Integer , Employee> dataStore = instance.getMap("employeeMap");
        dataStore.put(employee.getId(), employee);
    }

    @Override
    public void addEmployees(Map<Integer, Employee> map) {

        IMap<Integer , Employee> dataStore = instance.getMap("employeeMap");
        dataStore.putAll(map);
    }

    @Override
    public List<Employee> listEmployees() {
        IMap<Integer , Employee> dataStore = instance.getMap("employeeMap");
        return new ArrayList<Employee>(dataStore.values());
    }

    public void shutDown(){
        instance.getLifecycleService().shutdown();
    }
}
