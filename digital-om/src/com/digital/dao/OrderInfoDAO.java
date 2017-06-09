package com.digital.dao;

import java.util.List;

import com.digital.entity.*;

public interface OrderInfoDAO {
	// ����ҳ�롢ÿҳ��¼���Ͳ�ѯ��������װ��OrderInfo�����У���ȡ����������Ϣ�б�
	public List<OrderInfo> getAllOrderInfoByPage(int pageIndex, int pageSize,
			OrderInfo oi);
	
	// ���ݶ���id�Ż�ȡ��������
	public OrderInfo getOrderInfoById(int id);

	// ����ÿҳ��¼���Ͳ�ѯ��������װ��OrderInfo�����У�������ҳ��
	public int getTotalPages(int pageSize, OrderInfo oi);
	
	// ���ݲ�ѯ��������װ��OrderInfo�����У������ܼ�¼��
	public int getTotalCount(OrderInfo oi);

	// ��Ӷ�������Ͷ�����ϸ��¼
	public int addOrder(OrderInfo oi);	
	
	// ɾ����������
	public int deleteOrder(OrderInfo oi);
	
	// ���ݶ���id�Ż�ȡ������ϸ�б�
	public List<OrderDetail> getOrderDetailByOid(int oid);
	
	// �޸Ķ�����Ϣ	
	public int modifyOrder(OrderInfo oi);
	
	// ɾ��������ϸ
	public int deleteOrderDetail(OrderDetail od);
}
