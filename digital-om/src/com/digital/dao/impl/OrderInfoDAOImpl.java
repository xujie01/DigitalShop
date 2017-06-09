package com.digital.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.digital.dao.OrderInfoDAO;
import com.digital.entity.OrderDetail;
import com.digital.entity.OrderInfo;

@Repository("orderInfoDAO")
public class OrderInfoDAOImpl implements OrderInfoDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<OrderInfo> getAllOrderInfoByPage(int pageIndex, int pageSize,
			OrderInfo oi) {
		String hql = "from OrderInfo oi where 1=1";
		if (oi != null) {
			if (oi.getId() > 0) {
				hql += " and oi.id=" + oi.getId();
			} else {
				if (oi.getStatus() != null && !"ÇëÑ¡Ôñ".equals(oi.getStatus())) {
					hql += " and oi.status='" + oi.getStatus() + "'";
				}
				if (oi.getOrderTimeFrom() != null
						&& !"".equals(oi.getOrderTimeFrom())) {
					hql += " and oi.ordertime>='" + oi.getOrderTimeFrom() + "'";
				}
				if (oi.getOrderTimeTo() != null
						&& !"".equals(oi.getOrderTimeTo())) {
					hql += " and oi.ordertime<'" + oi.getOrderTimeTo() + "'";
				}
				if (oi.getUid() > 0) {
					hql += " and oi.ui.id=" + oi.getUid();
				}
			}
		}
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.setFirstResult((pageIndex - 1) * pageSize)
				.setMaxResults(pageSize).list();
	}

	@Override
	public int getTotalPages(int pageSize, OrderInfo oi) {
		int count = 0;
		int totalPagas = 0;
		String hql = "select count(oi) from OrderInfo oi where 1=1";
		if (oi != null) {
			if (oi.getId() > 0) {
				hql += " and oi.id=" + oi.getId();
			} else {
				if (oi.getStatus() != null && !"ÇëÑ¡Ôñ".equals(oi.getStatus())) {
					hql += " and oi.status='" + oi.getStatus() + "'";
				}
				if (oi.getOrderTimeFrom() != null
						&& !"".equals(oi.getOrderTimeFrom())) {
					hql += " and oi.ordertime>='" + oi.getOrderTimeFrom() + "'";
				}
				if (oi.getOrderTimeTo() != null
						&& !"".equals(oi.getOrderTimeTo())) {
					hql += " and oi.ordertime<'" + oi.getOrderTimeTo() + "'";
				}
				if (oi.getUid() > 0) {
					hql += " and oi.ui.id=" + oi.getUid();
				}
			}
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		count = ((Long) query.uniqueResult()).intValue();
		totalPagas = (count % pageSize == 0) ? (count / pageSize) : (count
				/ pageSize + 1);
		return totalPagas;
	}

	@Override
	public int getTotalCount(OrderInfo oi) {
		int count = 0;
		String hql = "select count(oi) from OrderInfo oi where 1=1";
		if (oi != null) {
			if (oi.getId() > 0) {
				hql += " and oi.id=" + oi.getId();
			} else {
				if (oi.getStatus() != null && !"ÇëÑ¡Ôñ".equals(oi.getStatus())) {
					hql += " and oi.status='" + oi.getStatus() + "'";
				}
				if (oi.getOrderTimeFrom() != null
						&& !"".equals(oi.getOrderTimeFrom())) {
					hql += " and oi.ordertime>='" + oi.getOrderTimeFrom() + "'";
				}
				if (oi.getOrderTimeTo() != null
						&& !"".equals(oi.getOrderTimeTo())) {
					hql += " and oi.ordertime<'" + oi.getOrderTimeTo() + "'";
				}
				if (oi.getUid() > 0) {
					hql += " and oi.ui.id=" + oi.getUid();
				}
			}
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public int addOrder(OrderInfo oi) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(oi);
	}

	@Override
	public int deleteOrder(OrderInfo oi) {
		Session session = sessionFactory.getCurrentSession();
		// Query query =
		// session.createQuery("delete OrderInfo where id in "+ids);
		try {
			session.delete(oi);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public OrderInfo getOrderInfoById(int id) {
		String hql = "from OrderInfo oi where oi.id=" + id;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return (OrderInfo) query.uniqueResult();
	}

	@Override
	public List<OrderDetail> getOrderDetailByOid(int oid) {
		String hql = "from OrderDetail od where od.oi.id=" + oid;
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public int modifyOrder(OrderInfo oi) {	
		
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(oi);
		} catch (Exception e) {
			return 0;
		}
		return 1;		
	}

	@Override
	public int deleteOrderDetail(OrderDetail od) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(od);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}
