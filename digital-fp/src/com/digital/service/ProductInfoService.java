package com.digital.service;

import java.util.List;
import com.digital.entity.ProductInfo;;

public interface ProductInfoService {

	public List<ProductInfo> getAllProductInfo();
	
	public ProductInfo getProductInfoByPiId(int piId);
	
	//根据商品ids字符串查询已经浏览过的商品列表
	public List<ProductInfo> getBrowsingProductInfo(String ids);
	
}	
