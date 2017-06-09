package com.digital.dao;

import java.util.List;

import com.digital.entity.*;

public interface ProductInfoDAO {
	// 获取在售产品列表，即数据表product_info中status字段值为1的记录
	public List<ProductInfo> getOnSaleProduct();
	// 根据产品id获取产品对象
	public ProductInfo getProductInfoById(int id);
}
