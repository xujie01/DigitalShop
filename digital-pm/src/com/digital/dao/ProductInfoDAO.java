package com.digital.dao;

import java.util.List;

import com.digital.entity.*;

public interface ProductInfoDAO {
	public List<ProductInfo> getByPage(int pageIndex,int pageSize,ProductInfo pi);
	public ProductInfo getById(int id);
	public int getTotalPages(int pageSize,ProductInfo pi);
	public int getTotalCount(ProductInfo pi);
	public int updateStatus(String ids);
	public int add(ProductInfo pi);
	public void update(ProductInfo pi);
}
