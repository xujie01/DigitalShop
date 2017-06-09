package com.digital.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.digital.dao.ProductInfoDAO;
import com.digital.entity.ProductInfo;

public class ProductInfoDAOImpl implements ProductInfoDAO {
	
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<ProductInfo> getAll() {		
		return sessionFactory.getCurrentSession().createCriteria(ProductInfo.class).list();
	}

	@Override
	public ProductInfo getProductInfoByPiId(int piId) {
		Session session=sessionFactory.getCurrentSession();	
		return (ProductInfo)session.get(ProductInfo.class, piId);
	}

	@Override
	public List<ProductInfo> getByPids(String pids) {
		Session session=sessionFactory.getCurrentSession();	
		Query query=session.createQuery("from ProductInfo pi where pi.id in"+pids);
		return query.list();
	}

}
