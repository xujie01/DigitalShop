package com.digital.pojo;

import java.util.HashSet;
import java.util.Set;

public class AdminInfo {
	private int id;
	private String name;
	private String pwd;
	private int role;
	
	// 关联的属性
	private Set fs = new HashSet();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	public Set getFs() {
		return fs;
	}
	public void setFs(Set fs) {
		this.fs = fs;
	}
	
	public AdminInfo() {
	}
	
}
