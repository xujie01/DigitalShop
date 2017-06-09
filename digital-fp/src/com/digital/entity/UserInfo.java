package com.digital.entity;

import java.util.Date;

public class UserInfo {
	private int id;
	private String userName;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	// 无参构造
	public UserInfo() {
	}

	// 有参构造
	public UserInfo(String userName, String password, Date regDate) {
		this.userName = userName;
		this.password = password;
	}

}
