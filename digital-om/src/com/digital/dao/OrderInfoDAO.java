package com.digital.dao;

import java.util.List;

import com.digital.entity.*;

public interface OrderInfoDAO {
	// 根据页码、每页记录数和查询条件（封装在OrderInfo对象中）获取订单订单信息列表
	public List<OrderInfo> getAllOrderInfoByPage(int pageIndex, int pageSize,
			OrderInfo oi);
	
	// 根据订单id号获取订单对象
	public OrderInfo getOrderInfoById(int id);

	// 根据每页记录数和查询条件（封装在OrderInfo对象中）计算总页数
	public int getTotalPages(int pageSize, OrderInfo oi);
	
	// 根据查询条件（封装在OrderInfo对象中）计算总记录数
	public int getTotalCount(OrderInfo oi);

	// 添加订单主表和订单明细记录
	public int addOrder(OrderInfo oi);	
	
	// 删除订单对象
	public int deleteOrder(OrderInfo oi);
	
	// 根据订单id号获取订单明细列表
	public List<OrderDetail> getOrderDetailByOid(int oid);
	
	// 修改订单信息	
	public int modifyOrder(OrderInfo oi);
	
	// 删除订单明细
	public int deleteOrderDetail(OrderDetail od);
}
