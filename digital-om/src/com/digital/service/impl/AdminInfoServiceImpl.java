package com.digital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.dao.AdminInfoDAO;
import com.digital.entity.AdminInfo;
import com.digital.service.AdminInfoService;

@Service("adminInfoService")
@Transactional
public class AdminInfoServiceImpl implements AdminInfoService {

	@Autowired
	AdminInfoDAO adminInfoDAO;	
	@Override
	public List<AdminInfo> login(AdminInfo cond) {
		return adminInfoDAO.search(cond);
	}

}
