package com.digital.service.impl;

import java.util.List;

import com.digital.dao.OrderInfoDAO;
import com.digital.entity.OrderInfo;
import com.digital.service.OrderInfoService;

public class OrderInfoServiceImpl implements OrderInfoService {
	
	OrderInfoDAO orderInfoDAO;
	public void setOrderInfoDAO(OrderInfoDAO orderInfoDAO) {
		this.orderInfoDAO = orderInfoDAO;
	}
	
	@Override
	public List getOrderInfoByUserInfoId(int userInfoId) {
		
		return orderInfoDAO.getOrderInfoByUserInfoId(userInfoId);
	}

	@Override
	public void deleteOrderInfoById(int id) {
		OrderInfo orderInfo=orderInfoDAO.getOrderInfoById(id);
		orderInfoDAO.deleteOrderInfo(orderInfo);
	}

}
