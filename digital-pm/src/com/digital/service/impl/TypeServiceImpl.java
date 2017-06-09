package com.digital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.dao.*;
import com.digital.entity.Type;
import com.digital.service.TypeService;

@Service("typeService")
@Transactional
public class TypeServiceImpl implements TypeService {

	@Autowired
	TypeDAO typeDAO;
	
	@Override
	public List<Type> getAll() {
		return typeDAO.getAll();
	}

	@Override
	public List<Type> getByPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return typeDAO.getByPage(pageIndex, pageSize);
	}

	@Override
	public int getTotalPages(int pageSize) {
		// TODO Auto-generated method stub
		return typeDAO.getTotalPages(pageSize);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return typeDAO.getTotalCount();
	}

	@Override
	public int addType(Type type) {
		// TODO Auto-generated method stub
		return typeDAO.add(type);
	}

	@Override
	public void updateType(Type type) {
		typeDAO.update(type);		
	}

	@Override
	public Type getById(int id) {
		return typeDAO.getById(id);
	}

}
