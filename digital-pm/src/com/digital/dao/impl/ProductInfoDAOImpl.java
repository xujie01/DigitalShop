package com.digital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.digital.dao.ProductInfoDAO;
import com.digital.entity.ProductInfo;

@Repository("productInfoDAO")
public class ProductInfoDAOImpl implements ProductInfoDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<ProductInfo> getByPage(int pageIndex, int pageSize,
			ProductInfo pi) {
		List<ProductInfo> piList = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ProductInfo.class);
		
		if (pi != null) {
			if (pi.getCode() != null && !"".equals(pi.getCode())) {
				c.add(Restrictions.eq("code", pi.getCode()));
			} else {
				if (pi.getName() != null && !"".equals(pi.getName())) {
					c.add(Restrictions.like("name", pi.getName(),
							MatchMode.ANYWHERE));
				}
				if (pi.getBrand() != null && !"".equals(pi.getBrand())) {
					c.add(Restrictions.like("brand", pi.getBrand(),
							MatchMode.ANYWHERE));
				}
				if (pi.getType() != null && pi.getType().getId() > 0) {
					c.add(Restrictions.eq("type.id", pi.getType().getId()));
				}
				if (pi.getPriceFrom() > 0) {
					c.add(Restrictions.gt("price", pi.getPriceFrom()));
				}
				if (pi.getPriceTo() > 0) {
					c.add(Restrictions.le("price", pi.getPriceTo()));
				}
			}
		}

		int startIndex = (pageIndex - 1) * pageSize;
		c.setFirstResult(startIndex);
		c.setMaxResults(pageSize);
		return c.list();
	}

	@Override
	public int getTotalPages(int pageSize, ProductInfo pi) {
		int count = 0;
		int totalPagas = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ProductInfo.class);
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.rowCount());
		c.setProjection(pList);

		if (pi != null) {
			if (pi.getCode() != null && !"".equals(pi.getCode())) {
				c.add(Restrictions.eq("code", pi.getCode()));
			} else {
				if (pi.getName() != null && !"".equals(pi.getName())) {
					c.add(Restrictions.like("name", pi.getName(),
							MatchMode.ANYWHERE));
				}
				if (pi.getBrand() != null && !"".equals(pi.getBrand())) {
					c.add(Restrictions.like("brand", pi.getBrand(),
							MatchMode.ANYWHERE));
				}
				if (pi.getType() != null && pi.getType().getId() > 0) {
					c.add(Restrictions.eq("type.id", pi.getType().getId()));
				}
				if (pi.getPriceFrom() > 0) {
					c.add(Restrictions.gt("price", pi.getPriceFrom()));
				}
				if (pi.getPriceTo() > 0) {
					c.add(Restrictions.le("price", pi.getPriceTo()));
				}
			}
		}


		count = ((Long) c.uniqueResult()).intValue();
		totalPagas = (count % pageSize == 0) ? (count / pageSize) : (count
				/ pageSize + 1);

		return totalPagas;
	}

	@Override
	public int getTotalCount(ProductInfo pi) {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ProductInfo.class);
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.rowCount());
		c.setProjection(pList);

		if (pi != null) {
			if (pi.getCode() != null && !"".equals(pi.getCode())) {
				c.add(Restrictions.eq("code", pi.getCode()));
			} else {
				if (pi.getName() != null && !"".equals(pi.getName())) {
					c.add(Restrictions.like("name", pi.getName(),
							MatchMode.ANYWHERE));
				}
				if (pi.getBrand() != null && !"".equals(pi.getBrand())) {
					c.add(Restrictions.like("brand", pi.getBrand(),
							MatchMode.ANYWHERE));
				}
				if (pi.getType() != null && pi.getType().getId() > 0) {
					c.add(Restrictions.eq("type.id", pi.getType().getId()));
				}
				if (pi.getPriceFrom() > 0) {
					c.add(Restrictions.gt("price", pi.getPriceFrom()));
				}
				if (pi.getPriceTo() > 0) {
					c.add(Restrictions.le("price", pi.getPriceTo()));
				}
			}
		}


		count = ((Long) c.uniqueResult()).intValue();

		return count;
	}

	@Override
	public int updateStatus(String ids) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update ProductInfo pi set pi.status=0 where pi.id in "
				+ ids;
		Query query = session.createQuery(hql);
		return query.executeUpdate();
	}

	@Override
	public int add(ProductInfo pi) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(pi);
	}

	@Override
	public void update(ProductInfo pi) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(pi);
	}

	@Override
	public ProductInfo getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		ProductInfo pi = session.get(ProductInfo.class, id);
		return pi;
	}
}
