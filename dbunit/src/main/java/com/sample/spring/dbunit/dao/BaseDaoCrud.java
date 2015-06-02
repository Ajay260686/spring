package com.sample.spring.dbunit.dao;

import java.util.List;

import com.sample.spring.dbunit.dao.exception.DaoException;

public interface BaseDaoCrud<D, PK> extends BaseDao {

	PK insert(D dto) throws DaoException;
	
	void update(PK pk , D dto) throws DaoException;
	
	void delete(PK pk) throws DaoException;
	
	D findByPrimaryKey(PK pk) throws DaoException;
	
	D findByPrimaryKey(int id) throws DaoException;
	
	List<D> findAll() throws DaoException;
	
	long countAll();
	
	List<D> findAllByPage(long offset, long limit) throws DaoException;
}
