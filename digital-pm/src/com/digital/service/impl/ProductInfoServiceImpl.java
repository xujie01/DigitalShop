package com.digital.service.impl;

import java.util.List;

import javax.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import com.digital.dao.*;
import com.digital.entity.*;
import com.digital.service.ProductInfoService;

@Service("productInfoService")
@Transactional
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	ProductInfoDAO productInfoDAO;

	public void setProductInfoDAO(ProductInfoDAO productInfoDAO) {
		this.productInfoDAO = productInfoDAO;
	}

	@Override
	public List<ProductInfo> getByPage(int pageIndex, int pageSize,ProductInfo pi) {
		return productInfoDAO.getByPage(pageIndex, pageSize,pi);
	}

	@Override
	public int getTotalPages(int pageSize,ProductInfo pi) {		
		return productInfoDAO.getTotalPages(pageSize,pi);
	}

	@Override
	public int getTotalCount(ProductInfo pi) {		
		return productInfoDAO.getTotalCount(pi);
	}

	@Override
	public int updateStatus(String ids) {
		return productInfoDAO.updateStatus(ids);
	}

	@Override
	public int addProductInfo(ProductInfo pi) {
		return productInfoDAO.add(pi);
	}

	@Override
	public void updateProductInfo(ProductInfo pi) {
		productInfoDAO.update(pi);		
	}

	@Override
	public ProductInfo getById(int id) {
		return productInfoDAO.getById(id);
	}

}
