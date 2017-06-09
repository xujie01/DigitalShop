package com.digital.dao;

import java.util.List;

import com.digital.entity.ProductInfo;

public interface ProductInfoDAO {

	//得到所有商品列表
	public List<ProductInfo> getAll();
	
	//根据商品id查询商品信息
	public ProductInfo getProductInfoByPiId(int piId);
	//根据商品id的字符串查询商品列表
	public List<ProductInfo> getByPids(String pids);
	
}
