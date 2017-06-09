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

//使用@Repository注解在Spring容器中注册实例名为userInfoDAO的UserInfoDAOImpl实例
@Repository("userInfoDAO")
public class UserInfoDAOImpl implements UserInfoDAO {

	// 通过@Autowired注解注入Spring容器中的SessionFactory实例
	@Autowired
	SessionFactory sessionFactory;

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

	// 获取系统合法用户,即数据表user_info中status=1的用户
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
