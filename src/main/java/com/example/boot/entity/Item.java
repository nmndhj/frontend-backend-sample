package com.example.boot.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 100)
	private String imgPath;
	
	@Column
	private int price;

	@Column
	private int discountPer;

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getImgPath() {
//		return imgPath;
//	}
//
//	public void setImgPath(String imgPath) {
//		this.imgPath = imgPath;
//	}
//
//	public int getPrice() {
//		return price;
//	}
//
//	public void setPrice(int price) {
//		this.price = price;
//	}
//
//	public int getDiscountPer() {
//		return discountPer;
//	}
//
//	public void setDiscountPer(int discountPer) {
//		this.discountPer = discountPer;
//	}

	
}