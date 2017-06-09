package com.digital.service;

import java.util.List;

import com.digital.entity.OrderInfo;
import com.digital.entity.ProductInfo;

public interface ProductInfoService {
	public List<ProductInfo> getOnSaleProduct();
	public ProductInfo getProductInfoById(int id);
}
