package com.digital.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.*;
import com.digital.pojo.*;
import com.digital.service.*;

@Service("adminInfoService")
public class AdminInfoServiceImpl implements AdminInfoService {

	@Autowired
	private AdminInfoDAO adminInfoDAO;
	
	@Override
	public AdminInfo getAdminInfoByCond(AdminInfo ai) {
		return adminInfoDAO.getAdminInfoByCond(ai);
	}

	@Override
	public AdminInfo getAdminInfoFunctions(int id) {
		return adminInfoDAO.getAdminInfoFunctions(id);
	}

	@Override
	public List<AdminInfo> getAllAdminInfo() {		
		return adminInfoDAO.getAllAdminInfo();
	}

	@Override
	public void addAdminInfo(AdminInfo ai) {
		adminInfoDAO.addAdminInfo(ai);
	}

}
