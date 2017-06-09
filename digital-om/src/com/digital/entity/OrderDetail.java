package com.digital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_detail", catalog = "digital")
public class OrderDetail {
	private int id;
	private OrderInfo oi;
	private ProductInfo pi;
	private int num;	
	private double price;
	private double totalprice;
	private int pid;
	
	@Transient
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Transient
	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	@Transient
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@JsonIgnoreProperties(value = { "ui","ods"}) 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "oid")
	public OrderInfo getOi() {
		return oi;
	}

	public void setOi(OrderInfo oi) {
		this.oi = oi;
	}
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "pid", unique = true)
	public ProductInfo getPi() {
		return pi;
	}

	public void setPi(ProductInfo pi) {
		this.pi = pi;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public OrderDetail() {
	}
	
	

}
