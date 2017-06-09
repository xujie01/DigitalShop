package com.digital.entity;

import java.util.HashSet;
import java.util.Set;

public class OrderInfo {
	
	private Integer id;
	private UserInfo userInfo;
	private String status;
	private String ordertime;
	private Double orderprice;
	
	private Set orderDetails=new HashSet(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public Double getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(Double orderprice) {
		this.orderprice = orderprice;
	}

	public Set getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderInfo() {

	}

	public OrderInfo(Integer id, UserInfo userInfo, String status,
			String ordertime, Double orderprice, Set orderDetails) {
		super();
		this.id = id;
		this.userInfo = userInfo;
		this.status = status;
		this.ordertime = ordertime;
		this.orderprice = orderprice;
		this.orderDetails = orderDetails;
	}


	
	
}
