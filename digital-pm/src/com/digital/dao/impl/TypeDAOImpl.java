package com.digital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.digital.dao.TypeDAO;
import com.digital.entity.Type;

@Repository("typeDAO")
public class TypeDAOImpl implements TypeDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Type> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Type.class);
		return c.list();
	}

	@Override
	public List<Type> getByPage(int pageIndex, int pageSize) {
		List<Type> typeList = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Type.class);
		int startIndex = (pageIndex - 1) * pageSize;
		c.setFirstResult(startIndex);
		c.setMaxResults(pageSize);
		return c.list();
	}

	@Override
	public int getTotalPages(int pageSize) {
		int count = 0;
		int totalPagas = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Type.class);
		ProjectionList tList = Projections.projectionList();
		tList.add(Projections.rowCount());
		c.setProjection(tList);
		count = ((Long) c.uniqueResult()).intValue();
		totalPagas = (count % pageSize == 0) ? (count / pageSize) : (count
				/ pageSize + 1);
		return totalPagas;
	}

	@Override
	public int getTotalCount() {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Type.class);
		ProjectionList tList = Projections.projectionList();
		tList.add(Projections.rowCount());
		c.setProjection(tList);
		count = ((Long) c.uniqueResult()).intValue();
		return count;
	}

	@Override
	public int add(Type type) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(type);
	}

	@Override
	public void update(Type type) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(type);		
	}

	@Override
	public Type getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Type type = session.get(Type.class, id);
		return type;
	}
}
