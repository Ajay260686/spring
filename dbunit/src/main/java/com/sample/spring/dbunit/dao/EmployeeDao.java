package com.sample.spring.dbunit.dao;

import java.util.List;

import com.sample.spring.dbunit.dao.exception.DaoException;
import com.sample.spring.dbunit.domain.Employee;
import com.sample.spring.dbunit.domain.EntityPkId;

public interface EmployeeDao extends BaseDaoCrud<Employee, EntityPkId> {

	public List<Employee> findWhereEmployeeNameEquals(String employeeName) throws DaoException;
	
	public Employee findByPrimaryKey(EntityPkId pk) throws DaoException;
}
