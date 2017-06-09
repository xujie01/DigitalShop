package com.digital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.dao.*;
import com.digital.entity.*;
import com.digital.service.OrderInfoService;

@Service("orderInfoService")
//使用@Transactional注解实现事务管理
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	OrderInfoDAO orderInfoDAO;
	
	@Override
	public List<OrderInfo> getAllOrderInfoByPage(int pageIndex, int pageSize, OrderInfo oi) {
		return orderInfoDAO.getAllOrderInfoByPage(pageIndex,pageSize,oi);
	}

	@Override
	public int getTotalPages(int pageSize, OrderInfo oi) {
		return orderInfoDAO.getTotalPages(pageSize,oi);
	}

	@Override
	public int getTotalCount(OrderInfo oi) {		
		return orderInfoDAO.getTotalCount(oi);
	}

	@Override
	public int addOrder(OrderInfo oi) {
		return orderInfoDAO.addOrder(oi);
	}

	@Override
	public int deleteOrder(OrderInfo oi) {
		return orderInfoDAO.deleteOrder(oi);
	}

	@Override
	public OrderInfo getOrderInfoById(int id) {
		return orderInfoDAO.getOrderInfoById(id);
	}

	@Override
	public List<OrderDetail> getOrderDetailByOid(int oid) {		
		return orderInfoDAO.getOrderDetailByOid(oid);
	}

	@Override
	public int modifyOrder(OrderInfo oi) {
		return orderInfoDAO.modifyOrder(oi);
	}

	@Override
	public int deleteOrderDetail(OrderDetail od) {
		// TODO Auto-generated method stub
		return orderInfoDAO.deleteOrderDetail(od);
	}

}
