package com.digital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.digital.dao.OrderDetailDAO;
import com.digital.entity.OrderDetail;

public class OrderDetailDAOImpl implements OrderDetailDAO {
	
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addOrderDetail(OrderDetail orderDetail) {
		
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(orderDetail);
	}

	@Override
	public List getOrderDetailById(int id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(OrderDetail.class);
		c.add(Restrictions.eq("orderInfo.id", id));
		return c.list();
	}
	
	

}
