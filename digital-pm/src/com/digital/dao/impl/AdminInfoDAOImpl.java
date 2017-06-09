package com.digital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.digital.dao.AdminInfoDAO;
import com.digital.entity.AdminInfo;

@Repository("adminInfoDAO")
public class AdminInfoDAOImpl implements AdminInfoDAO {

	// ͨ��@Autowiredע��ע��Spring�����е�SessionFactoryʵ��
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<AdminInfo> search(AdminInfo cond) {
		List<AdminInfo> aiList = null;
		// ���session
		Session session = sessionFactory.getCurrentSession();
		// ����Criteria����
		Criteria c = session.createCriteria(AdminInfo.class);
		// ʹ��Example�����ഴ��ʾ������
		Example example = Example.create(cond);
		// ΪCriteria����ָ��ʾ������example��Ϊ��ѯ����
		c.add(example);
		//c.add(Restrictions.eq("name", cond.getName())).add(Restrictions.eq("pwd", cond.getPwd()));
		// ִ�в�ѯ
		aiList = c.list();
		// ���ؽ��
		return aiList;
	}

}
