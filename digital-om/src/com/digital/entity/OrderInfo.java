package com.digital.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_info", catalog = "digital")
public class OrderInfo {
	private int id;
	private UserInfo ui;
	private String status;
	private String ordertime;
	private double orderprice;	
	private String orderTimeFrom;
	private String orderTimeTo;
	private int uid;
	
	@Transient	
	public String getOrderTimeFrom() {
		return orderTimeFrom;
	}
	public void setOrderTimeFrom(String orderTimeFrom) {
		this.orderTimeFrom = orderTimeFrom;
	}
	
	@Transient	
	public String getOrderTimeTo() {
		return orderTimeTo;
	}
	public void setOrderTimeTo(String orderTimeTo) {
		this.orderTimeTo = orderTimeTo;
	}	
	
	@Transient	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@JsonIgnoreProperties(value = { "oi","pi"}) 
	private Set<OrderDetail> ods = new HashSet<OrderDetail>();	
	@OneToMany(mappedBy = "oi",fetch=FetchType.EAGER,cascade = { CascadeType.ALL})
	public Set<OrderDetail> getOds() {
		return ods;
	}
	public void setOds(Set<OrderDetail> ods) {
		this.ods = ods;
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@JsonIgnoreProperties(value = { "orders"}) 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "uid")
	public UserInfo getUi() {
		return ui;
	}
	public void setUi(UserInfo ui) {
		this.ui = ui;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	
	public double getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(double orderprice) {
		this.orderprice = orderprice;
	}
	
	public OrderInfo() {

	}

}
