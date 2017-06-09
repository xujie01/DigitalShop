package com.digital.entity;

import java.util.HashSet;
import java.util.Set;

public class ProductInfo {
	
	private Integer id;
	private Type type;
	private String code;
	private String name;
	private String brand;
	private String pic;
	private Integer num;
	private Double price;
	private String intro;
	private Integer status;
	private String bigpic;
	//以后添加订单详细信息
	private Set orderDetails=new HashSet(0);
	
	public Set getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBigpic() {
		return bigpic;
	}
	public void setBigpic(String bigpic) {
		this.bigpic = bigpic;
	}
	
	public ProductInfo() {
		super();
	}
	
	
}
