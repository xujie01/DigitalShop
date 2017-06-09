package com.digital.dao;

import java.util.Map;
import java.util.List;

import com.digital.pojo.UserInfo;

public interface UserInfoDAO {
	public UserInfo getUserInfoByCond(UserInfo ui); 
	public List<UserInfo> getUserListByPage(Map map);	
	public List<UserInfo> getAllUser(String userName);
	public void updateUserStatus(Map map);
}
