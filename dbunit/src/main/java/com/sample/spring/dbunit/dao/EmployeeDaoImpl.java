package com.sample.spring.dbunit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.sample.spring.dbunit.dao.exception.DaoException;
import com.sample.spring.dbunit.domain.Employee;
import com.sample.spring.dbunit.domain.EntityPkId;

public class EmployeeDaoImpl extends AbstractDao<Employee, EntityPkId>
		implements EmployeeDao {
	
	private static final String SQL_SELECT_COLUMNS = "SELECT "
			+ "employees.emp_no, employees.first_name, employees.last_name FROM ";
	
	@Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public EmployeeDaoImpl() {
		super(Employee.class, EntityPkId.class);
	}

	public void update(EntityPkId pk, Employee dto) throws DaoException {
		// TODO Auto-generated method stub

	}

	public Employee findByPrimaryKey(EntityPkId pk) throws DaoException {
		return findByPrimaryKey(pk.getId());
	}

	public long countAll() {
		return getJdbcTemplate().queryForObject(
				"select count(*) from " + getTableName(), null, Long.class);
	}

	public String getTableName() {
		return "employees";
	}

	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

		Employee dto = new Employee();
		dto.setEmpNo(rs.getInt(1));
		dto.setFirstName(rs.getString(2));
		dto.setLastName(rs.getString(3));
		return dto;

	}

	@Override
	protected String getFindByPrimaryKeyQuery() {
		return SQL_SELECT_COLUMNS + getTableName() + " WHERE emp_no = ?";
	}

	@Override
	protected String getFindAllQuery() {
		return SQL_SELECT_COLUMNS + getTableName() + " ORDER BY emp_no";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO " + getTableName()
				+ " (emp_no, first_name, employees.last_name"
				+ ") VALUES (?, ?, ? )";
	}

	@Override
	protected Object[] getInsertParameters(Employee dto) {

		return new Object[] { dto.getEmpNo(), dto.getFirstName(),
				dto.getLastName() };

	}

	@Override
	protected String getUpdateQuery() {

		return "UPDATE " + getTableName()
				+ " SET emp_no = ?, first_name = ?, last_name = ? WHERE id = ?";

	}

	@Override
	protected Object[] getUpdateParameters(EntityPkId pk, Employee dto) {

		return new Object[] { dto.getEmpNo(), dto.getFirstName(),
				dto.getLastName() };

	}

	@Override
	protected EntityPkId getPkEntity(int id) {
		EntityPkId pk = new EntityPkId();
		pk.setId(id);
		return pk;
	}

	@Override
	protected String getSqlSelectColumns() {
		return SQL_SELECT_COLUMNS;
	}

	public List<Employee> findWhereEmployeeNameEquals(String employeeName)
			throws DaoException {
		try {
			return getJdbcTemplate().query(
					SQL_SELECT_COLUMNS + getTableName()
							+ " WHERE first_name = ?", this, employeeName);
		} catch (Exception e) {
			throw new DaoException("Couldn't retrieve employee with name"
					+ employeeName, e);
		}
	}

}
