package com.digital.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digital.dao.ProductInfoDAO;
import com.digital.entity.ProductInfo;

@Repository("productInfoDAO")
public class ProductInfoDAOImpl implements ProductInfoDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<ProductInfo> getOnSaleProduct() {	
		String hql = "from ProductInfo pi where pi.status=1"; 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql); 
		return query.list();
	}

	@Override
	public ProductInfo getProductInfoById(int id) {
		String hql = "from ProductInfo pi where pi.id="+id; 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql); 
		return (ProductInfo)query.uniqueResult();
	}

}
