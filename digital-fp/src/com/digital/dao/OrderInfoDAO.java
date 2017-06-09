package com.digital.dao;

import java.util.List;

import com.digital.entity.OrderInfo;

public interface OrderInfoDAO {
	
	//获取指定用户的订单列表
	public List getOrderInfoByUserInfoId(int userInfoId);
	
	//根据订单编号加载订单对象
	public OrderInfo getOrderInfoById(int id);
	//删除订单对象
	public void deleteOrderInfo(OrderInfo orderInfo);
	
}
