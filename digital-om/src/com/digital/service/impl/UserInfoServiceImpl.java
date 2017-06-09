package com.digital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digital.dao.UserInfoDAO;
import com.digital.entity.UserInfo;
import com.digital.service.UserInfoService;

//使用@Service注解在Spring容器中注册名为userInfoService的UserInfoServiceImpl实例
@Service("userInfoService")
//使用@Transactional注解实现事务管理
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

	//使用@Autowired注解注入UserInfoDAOImpl实例
	@Autowired
	UserInfoDAO userInfoDAO;	
	
	@Override
	public List<UserInfo> login(UserInfo cond) {
		return userInfoDAO.search(cond);
	}

	@Override
	public List<UserInfo> getValidUser() {
		return userInfoDAO.getValidUser();
	}

	@Override
	public UserInfo getUserInfoById(int id) {
		return userInfoDAO.getUserInfoById(id);
	}

}
