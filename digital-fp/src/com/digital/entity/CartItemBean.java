package com.digital.entity;

public class CartItemBean {
	private ProductInfo  pi;
	private int quantity;
	public void setPi(ProductInfo pi) {
		this.pi = pi;
	}
	public ProductInfo getPi() {
		return pi;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public CartItemBean(ProductInfo pi, int quantity) {
		super();
		this.pi = pi;
		this.quantity = quantity;
	}
	
}
