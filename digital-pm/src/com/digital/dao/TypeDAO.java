package com.digital.dao;

import java.util.List;

import com.digital.entity.*;

public interface TypeDAO {
	public List<Type> getAll();
	public Type getById(int id);
	public List<Type> getByPage(int pageIndex,int pageSize);	
	public int getTotalPages(int pageSize);
	public int getTotalCount();
	public int add(Type type);
	public void update(Type type);
}
