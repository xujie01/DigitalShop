package com.digital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital.dao.*;
import com.digital.entity.*;
import com.digital.service.*;

@Service("productInfoService")
//使用@Transactional注解实现事务管理
@Transactional
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	ProductInfoDAO productInfoDAO;
	
	@Override
	public List<ProductInfo> getOnSaleProduct() {
		return productInfoDAO.getOnSaleProduct();
	}

	@Override
	public ProductInfo getProductInfoById(int id) {
		return productInfoDAO.getProductInfoById(id);
	}
}
