package com.digital.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.*;
import com.digital.service.*;

@Service("powersService")
public class PowersServiceImpl implements PowersService {

	@Autowired
	private PowersDAO powersDAO;
	
	@Override
	public void delPowersByAdminid(int adminid) {
		powersDAO.delPowersByAdminid(adminid);
	}

	@Override
	public void addPowers(Map map) {
		powersDAO.addPowers(map);
	}

}
