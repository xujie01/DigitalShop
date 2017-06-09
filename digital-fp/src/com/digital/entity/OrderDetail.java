package com.digital.entity;

public class OrderDetail {
	
	private Integer id;
	private ProductInfo productInfo;
	private OrderInfo orderInfo;
	private Integer num;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public OrderDetail() {
	
	}
	
	public OrderDetail(Integer id, ProductInfo productInfo,
			OrderInfo orderInfo, Integer num) {
		super();
		this.id = id;
		this.productInfo = productInfo;
		this.orderInfo = orderInfo;
		this.num = num;
	}
	
	
	
}
