package com.digital.service;

import java.util.List;

import com.digital.entity.Type;

public interface TypeService {
	public List<Type> getAll();
	public Type getById(int id);
	public List<Type> getByPage(int pageIndex,int pageSize);	
	public int getTotalPages(int pageSize);
	public int getTotalCount();
	public int addType(Type type);
	public void updateType(Type type);
}
