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
		// 获得session
		Session session = sessionFactory.getCurrentSession();
		// 创建Criteria对象
		Criteria c = session.createCriteria(UserInfo.class);
		// 使用Example工具类创建示例对象
		Example example = Example.create(cond);
		// 为Criteria对象指定示例对象example作为查询条件
		c.add(example);
		// 执行查询
		uiList = c.list();
		// 返回结果
		return uiList;
	}

}
