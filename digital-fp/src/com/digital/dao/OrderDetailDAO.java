package com.digital.dao;

import java.util.List;

import com.digital.entity.OrderDetail;

public interface OrderDetailDAO {
	//生成订单明细表（订单子表）
	public void addOrderDetail(OrderDetail orderDetail);
	
	//根据订单信息表的编号获取订单明细列表
	public List getOrderDetailById(int id);
	
}
