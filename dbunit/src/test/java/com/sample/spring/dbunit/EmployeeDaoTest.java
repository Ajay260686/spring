package com.sample.spring.dbunit;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.sample.spring.dbunit.dao.EmployeeDao;
import com.sample.spring.dbunit.dao.exception.DaoException;
import com.sample.spring.dbunit.domain.Employee;

@DatabaseSetup("classpath:dbunit-employee.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class EmployeeDaoTest {

	@Autowired
	private EmployeeDao employeeDao;

	@Test
	public void getAllEmployees() throws DaoException {
		List<Employee> employees = employeeDao.findAll();
		Assert.assertEquals(4, employees.size());
	}
}
