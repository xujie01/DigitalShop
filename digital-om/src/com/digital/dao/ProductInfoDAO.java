package com.digital.dao;

import java.util.List;

import com.digital.entity.*;

public interface ProductInfoDAO {
	// ��ȡ���۲�Ʒ�б������ݱ�product_info��status�ֶ�ֵΪ1�ļ�¼
	public List<ProductInfo> getOnSaleProduct();
	// ���ݲ�Ʒid��ȡ��Ʒ����
	public ProductInfo getProductInfoById(int id);
}
