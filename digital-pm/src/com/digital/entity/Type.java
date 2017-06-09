package com.digital.entity;

import javax.persistence.*;

@Entity
@Table(name = "type", catalog = "digital")
public class Type {
	private int id; // ��Ʒ���ͱ��
	private String name; // ��Ʒ��������
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// �вι���
	public Type(String name) {
		this.name = name;
	}

	// �޲ι���
	public Type() {
	}

}
