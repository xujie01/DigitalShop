package com.digital.dao;

import java.util.List;

import com.digital.entity.UserInfo;

public interface UserInfoDAO {
	public List<UserInfo> search(UserInfo cond);
	
	// 获取系统合法用户，即数据表user_info中status字段为1的用户列表
	public List<UserInfo> getValidUser();
	// 根据用户id号获取用户对象
	public UserInfo getUserInfoById(int id);
}
