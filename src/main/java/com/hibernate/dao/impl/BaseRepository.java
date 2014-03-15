package com.hibernate.dao.impl;

import java.io.Serializable;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.hibernate.dao.IRepository;




public abstract class BaseRepository<M extends Serializable> extends HibernateDaoSupport implements IRepository<M> {

	

	public void save(M m) {
		// TODO Auto-generated method stub
//		System.out.println((Part)m.);
//		Part p = (Part)m;
//		System.out.println("Save in process: "+p.getCode());
		this.getSessionFactory().openSession().save(m);
	}
	
	public M findById(Class<M> c,long id) {
		return (M) this.getSessionFactory().openSession().get(c, id);
	}

}
