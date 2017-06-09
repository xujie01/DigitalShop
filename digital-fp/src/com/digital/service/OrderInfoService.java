package com.digital.service;

import java.util.List;

public interface OrderInfoService {
	
	//获取指定用户的订单列表
	public List getOrderInfoByUserInfoId(int userInfoId);
	//删除指定订单编号的订单
	public void deleteOrderInfoById(int id);
}
