package com.digital.dao;

import java.util.List;

import com.digital.entity.UserInfo;

public interface UserInfoDAO {
	public List<UserInfo> search(UserInfo cond);
	
	// ��ȡϵͳ�Ϸ��û��������ݱ�user_info��status�ֶ�Ϊ1���û��б�
	public List<UserInfo> getValidUser();
	// �����û�id�Ż�ȡ�û�����
	public UserInfo getUserInfoById(int id);
}
