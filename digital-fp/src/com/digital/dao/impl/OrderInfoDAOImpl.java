package com.digital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.digital.dao.OrderInfoDAO;
import com.digital.entity.OrderInfo;

public class OrderInfoDAOImpl implements OrderInfoDAO {
	
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List getOrderInfoByUserInfoId(int userInfoId) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(OrderInfo.class);
		c.add(Restrictions.eq("userInfo.id",userInfoId));
		return c.list();
	}

	@Override
	public OrderInfo getOrderInfoById(int id) {
		Session session=sessionFactory.getCurrentSession();
		OrderInfo orderInfo=(OrderInfo)session.get(OrderInfo.class, id);
		return orderInfo;
	}

	@Override
	public void deleteOrderInfo(OrderInfo orderInfo) {
		Session session=sessionFactory.getCurrentSession();

		session.delete(orderInfo);
	}

}
