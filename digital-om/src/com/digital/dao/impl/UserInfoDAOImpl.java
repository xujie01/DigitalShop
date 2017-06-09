package com.digital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digital.dao.UserInfoDAO;
import com.digital.entity.UserInfo;

//ʹ��@Repositoryע����Spring������ע��ʵ����ΪuserInfoDAO��UserInfoDAOImplʵ��
@Repository("userInfoDAO")
public class UserInfoDAOImpl implements UserInfoDAO {

	// ͨ��@Autowiredע��ע��Spring�����е�SessionFactoryʵ��
	@Autowired
	SessionFactory sessionFactory;

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

	// ��ȡϵͳ�Ϸ��û�,�����ݱ�user_info��status=1���û�
	@Override
	public List<UserInfo> getValidUser() {
		String hql = "from UserInfo ui where ui.status=1"; 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql); 
		return query.list();
	}

	@Override
	public UserInfo getUserInfoById(int id) {
		String hql = "from UserInfo ui where ui.id="+id; 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql); 
		return (UserInfo)query.uniqueResult();
	}

}
