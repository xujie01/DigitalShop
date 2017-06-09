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
	private String brand; //产品品牌
	private double price; //产品价格
	private int status;   //产品状态	
	private String pic;   //产品小图	
	private String bigpic;   //产品大图	
	private int num;      //产品数量
	private double priceFrom;	
	private double priceTo;	
	// 关联属性	
	private Type type;
	
	// 使用@ManyToOne和@JoinColumn注解实现ProductInfo到Type的多对一关联
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "tid")	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
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
	
	
	@Transient
	public double getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(double priceFrom) {
		this.priceFrom = priceFrom;
	}

	@Transient
	public double getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(double priceTo) {
		this.priceTo = priceTo;
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
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}	

	public String getBigpic() {
		return bigpic;
	}

	public void setBigpic(String bigpic) {
		this.bigpic = bigpic;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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
