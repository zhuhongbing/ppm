package com.hibernate.dao;

import java.io.Serializable;
import java.util.List;

public interface IRepository<M extends Serializable> {
	
	public M findById(Class<M> c,long id);

	public void save(M m);

	public void update(M m);

	public void delete(String id);

	public void delete(M m);

	public List<M> findAll();

	public List<M> query(String queryString);
}

