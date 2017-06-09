package com.digital.service;

import java.util.List;

import com.digital.entity.OrderDetail;
import com.digital.entity.OrderInfo;

public interface OrderInfoService {
	public List<OrderInfo> getAllOrderInfoByPage(int pageIndex, int pageSize, OrderInfo oi);
	public OrderInfo getOrderInfoById(int id);
	public int getTotalPages(int pageSize, OrderInfo oi);
	public int getTotalCount(OrderInfo oi);
	public int addOrder(OrderInfo oi);
	public int deleteOrder(OrderInfo oi);
	public List<OrderDetail> getOrderDetailByOid(int oid);
	public int modifyOrder(OrderInfo oi);
	public int deleteOrderDetail(OrderDetail od);
}
