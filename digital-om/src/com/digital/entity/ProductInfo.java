package com.digital.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_info", catalog = "digital")
public class ProductInfo {
	// 产品基本信息（部分）
	private int id; // 产品编号
	private String code; // 产品编码
	private String name; // 产品名称
	private String intro; // 产品介绍
	private String status; // 产品状态
	private double price; // 产品价格

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "code", nullable = false, length = 16)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "intro", nullable = false)
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// 无参构造
	public ProductInfo() {
	}

	// 有参构造
	public ProductInfo(String code, String name, String intro) {
		this.code = code;
		this.name = name;
		this.intro = intro;
	}

}
