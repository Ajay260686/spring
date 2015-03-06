package com.sample.spring.jersey.swagger.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by niyuj on 3/5/15.
 */
@XmlRootElement
public class EmployeeResponse extends AbstractGenericResponse<Employee>
{
    Employee result;

    @Override
    public Employee getResult() {
        return result;
    }

    @Override
    public void setResult(Employee employee) {
        result = employee;
    }
}

