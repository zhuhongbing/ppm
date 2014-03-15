package com.hibernate.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibernate.dao.impl.BaseRepository;

public abstract class BaseService<M extends Serializable> {
    @Autowired
    protected BaseRepository<M> repository;

    public void insert(M m) {
        repository.save(m);
    }
    
    public M findById(Class c,long id){
		return repository.findById(c, id);
		
    	
    }
    
}


