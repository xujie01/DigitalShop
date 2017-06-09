package com.digital.dao;

import java.util.Map;


public interface PowersDAO {
	public void delPowersByAdminid(int adminid);
	public void addPowers(Map map);	
}
