package com.digital.service;

import java.util.List;
import java.util.Map;

import com.digital.pojo.*;

public interface AdminInfoService {
	public AdminInfo getAdminInfoByCond(AdminInfo ai);
	public AdminInfo getAdminInfoFunctions(int id);
	public List<AdminInfo> getAllAdminInfo();
	public void addAdminInfo(AdminInfo ai);
}
