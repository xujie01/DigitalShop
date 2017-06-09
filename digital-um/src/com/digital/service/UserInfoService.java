package com.digital.service;

import java.util.Map;
import java.util.List;

import com.digital.pojo.UserInfo;

public interface UserInfoService {
	public UserInfo getUserInfoByCond(UserInfo ui); 
	public List<UserInfo> getAllUser(String userName);
	public List<UserInfo> getUserListByPage(Map map);	
	public void updateUserStatus(Map map);
}
