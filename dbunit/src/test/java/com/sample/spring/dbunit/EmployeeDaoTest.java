package com.sample.spring.dbunit;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.sample.spring.dbunit.dao.EmployeeDao;
import com.sample.spring.dbunit.dao.exception.DaoException;
import com.sample.spring.dbunit.domain.Employee;

@DatabaseSetup(value="classpath:dbunit-employee.xml", type=DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value="classpath:dbunit-employee.xml", type=DatabaseOperation.DELETE_ALL)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
						  DbUnitTestExecutionListener.class })
public class EmployeeDaoTest extends TestCase {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Test
	public void getAllEmployees() throws DaoException {
		List<Employee> employees = employeeDao.findAll();
		Assert.assertEquals(4, employees.size());
	}
}
