package com.digital.service;

import java.util.List;

import com.digital.entity.Type;

public interface TypeService {
	
	public List<Type> getAllWithDistinctBrand();
}
