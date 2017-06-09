package com.digital.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.UserInfoDAO;
import com.digital.pojo.UserInfo;
import com.digital.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDAO userInfoDAO;
	@Override
	public UserInfo getUserInfoByCond(UserInfo ui) {
		return userInfoDAO.getUserInfoByCond(ui);
	}
	@Override
	public List<UserInfo> getAllUser(String userName) {
		return userInfoDAO.getAllUser(userName);
	}
	@Override
	public List<UserInfo> getUserListByPage(Map map) {
		return userInfoDAO.getUserListByPage(map);
	}
	@Override
	public void updateUserStatus(Map map) {
		userInfoDAO.updateUserStatus(map);
	}
}
