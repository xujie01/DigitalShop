package com.digital.entity;

public class SearchProductInfo {
	// ��Ʒ������Ϣ�����֣�
	private int id; // ��Ʒ���
	private String code; // ��Ʒ����
	private String name; // ��Ʒ����
	private String brand; // ��ƷƷ��
	private double priceFrom; 
	private double priceTo;
	private int tid;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(double priceFrom) {
		this.priceFrom = priceFrom;
	}
	public double getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(double priceTo) {
		this.priceTo = priceTo;
	} 
	
}
