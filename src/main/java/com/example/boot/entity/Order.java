package com.example.boot.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private int memberId;

	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 500, nullable = false)
	private String address;
	
	@Column(length = 10, nullable = false)
	private String payment;
	
	@Column(length = 16)
	private String cardNumber;

	@Column(length = 500, nullable = false)
	private String items;

	
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public int getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(int memberId) {
//		this.memberId = memberId;
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
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getPayment() {
//		return payment;
//	}
//
//	public void setPayment(String payment) {
//		this.payment = payment;
//	}
//
//	public String getCardNumber() {
//		return cardNumber;
//	}
//
//	public void setCardNumber(String cardNumber) {
//		this.cardNumber = cardNumber;
//	}
//
//	public String getItems() {
//		return items;
//	}
//
//	public void setItems(String items) {
//		this.items = items;
//	}


	
	
	
	
}