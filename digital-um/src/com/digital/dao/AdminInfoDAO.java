package com.digital.dao;

import java.util.List;

import com.digital.pojo.*;

public interface AdminInfoDAO {
	public AdminInfo getAdminInfoByCond(AdminInfo ai);
	public AdminInfo getAdminInfoFunctions(int id);
	public List<AdminInfo> getAllAdminInfo();
	public void addAdminInfo(AdminInfo ai);
}
