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

	// 通过@Autowired注解注入Spring容器中的SessionFactory实例
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<AdminInfo> search(AdminInfo cond) {
		List<AdminInfo> aiList = null;
		// 获得session
		Session session = sessionFactory.getCurrentSession();
		// 创建Criteria对象
		Criteria c = session.createCriteria(AdminInfo.class);
		// 使用Example工具类创建示例对象
		Example example = Example.create(cond);
		// 为Criteria对象指定示例对象example作为查询条件
		c.add(example);
		//c.add(Restrictions.eq("name", cond.getName())).add(Restrictions.eq("pwd", cond.getPwd()));
		// 执行查询
		aiList = c.list();
		// 返回结果
		return aiList;
	}

}
