package com.sample.spring.jersey.swagger.beans;

import java.util.Collection;

public class EmployeeListResponse extends AbstractGenericResponse<Collection<Employee>> {

    Collection<Employee> result;

    @Override
    public Collection<Employee> getResult() {
        return result;
    }

    @Override
    public void setResult(Collection<Employee> employees) {
        result = employees;
    }
}
