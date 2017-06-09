package com.digital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digital.dao.UserInfoDAO;
import com.digital.entity.UserInfo;
import com.digital.service.UserInfoService;

//ʹ��@Serviceע����Spring������ע����ΪuserInfoService��UserInfoServiceImplʵ��
@Service("userInfoService")
//ʹ��@Transactionalע��ʵ���������
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

	//ʹ��@Autowiredע��ע��UserInfoDAOImplʵ��
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
