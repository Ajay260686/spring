package com.sample.spring.dbunit.dao;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sample.spring.dbunit.dao.exception.DaoException;

public abstract class AbstractDao<D, PK> extends
		JdbcDaoSupport implements BaseDaoCrud<D, PK>, RowMapper<D> {

	private static final int DEFAULT_OFFSET = 0;
	private static final int DEFAULT_LIMIT = 1000;

	private final Class<D> dtoClass;
	private final Class<PK> pkClass;
	
	protected abstract String getFindByPrimaryKeyQuery();

	protected abstract String getFindAllQuery();

	protected abstract String getInsertQuery();

	protected abstract Object[] getInsertParameters(D dto);

	protected abstract String getUpdateQuery();

	protected abstract Object[] getUpdateParameters(PK pk, D dto);

	protected abstract PK getPkEntity(int id);

	protected abstract String getSqlSelectColumns();

	public AbstractDao(Class<D> dtoClass, Class<PK> pkClass) {
		this.dtoClass = dtoClass;
		this.pkClass = pkClass;
	}

	public D findByPrimaryKey(int id) throws DaoException {

		D dto = null;
		try {
			List<D> list = getJdbcTemplate().query(getFindByPrimaryKeyQuery(),
					this, id);
			dto = list.size() == 0 ? null : list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public PK insert(D dto) throws DaoException {

		// Validate for generic/specific Data sanity
		int id = 0;
		try {
			getJdbcTemplate()
					.update(getInsertQuery(), getInsertParameters(dto));
			id = getJdbcTemplate().queryForObject("select last_insert_id()",
					null, Integer.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		PK pk = getPkEntity(id);
		return pk;
	}

	public List<D> findAll() throws DaoException {

		List<D> dlist = null;
		try {
			dlist = getJdbcTemplate().query(getFindAllQuery(), this);
		} catch (Exception ex) {
			handleException(ex);
		}
		return dlist;
	}

	public List<D> findAllByPage(long offset, long limit) throws DaoException {

		List<D> dlist = null;
		try {
			dlist = getJdbcTemplate().query(
					getFindAllByPageQuery(offset, limit), this, offset, limit);
		} catch (Exception ex) {
			handleException(ex);
		}
		return dlist;
	}

	protected void handleException(Exception exMain) throws DaoException {
			throw new RuntimeException("Dao method failed", exMain);
	}

	public void delete(PK pk) throws DaoException {
		try {
			getJdbcTemplate().update(
					"DELETE FROM " + getTableName() + " WHERE id = ?",
					getId(pk));
		} catch (Exception ex) {
			handleException(ex);
		}
	}

	private int getId(PK pk) throws Exception {

		Integer id;
		Method m = Class.forName(pk.getClass().getName()).getDeclaredMethod(
				"getId");
		if (m != null) {
			id = (Integer) m.invoke(pk);
		} else {
			throw new Exception("The class "
					+ Class.forName(pk.getClass().getName())
					+ " does not contain getId method!");
		}
		return id;
	}

	protected String getOffsetLimitSql(Integer offset, Integer limit) {

		if (offset != null && offset < 0) {
			offset = DEFAULT_OFFSET;
		}
		if (limit != null && limit <= 0) {
			limit = DEFAULT_LIMIT;
		}
		return " LIMIT "
				+ (offset != null ? offset.intValue() : DEFAULT_OFFSET + ", "
						+ limit != null ? limit.intValue() : DEFAULT_LIMIT);
	}

	protected String getFindAllByPageQuery(long offset, long limit) {
		return getFindAllQuery() + " limit ?,?";
	}
}
