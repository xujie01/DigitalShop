package com.digital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.*;
import com.digital.pojo.*;
import com.digital.service.*;


@Service("functionsService")
public class FunctionsServiceImpl implements FunctionsService {

	@Autowired
	private FunctionsDAO functionsDAO;
	
	@Override
	public List<Functions> getAllFunctions() {
		
		return functionsDAO.getAllFunctions();
	}

}
