package com.digital.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_info", catalog = "digital")
public class UserInfo {
	private int id;
	private String userName;
	private String password;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "userName", length = 16)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", length = 16)
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
	public UserInfo(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

}
