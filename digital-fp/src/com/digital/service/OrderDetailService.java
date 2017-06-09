package com.digital.service;

import java.util.List;

import com.digital.entity.OrderDetail;

public interface OrderDetailService {
	//生成订单明细表（订单子表）
	public void addOrderDetail(OrderDetail orderDetail);
	
	public List getOrderDetailById(int id);
}
