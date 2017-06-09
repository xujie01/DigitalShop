package com.digital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.digital.dao.UserInfoDAO;
import com.digital.entity.UserInfo;

public class UserInfoDAOImpl implements UserInfoDAO {

	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<UserInfo> search(UserInfo cond) {
		List<UserInfo> uiList = null;
		// ���session
		Session session = sessionFactory.getCurrentSession();
		// ����Criteria����
		Criteria c = session.createCriteria(UserInfo.class);
		// ʹ��Example�����ഴ��ʾ������
		Example example = Example.create(cond);
		// ΪCriteria����ָ��ʾ������example��Ϊ��ѯ����
		c.add(example);
		// ִ�в�ѯ
		uiList = c.list();
		// ���ؽ��
		return uiList;
	}

}
