package com.visualpath.cartservice.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserCart {
	
	long id;
	String name;
	String address;
	List<Product> prodList;
    long orderTotal;
    
public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public List<Product> getProdList() {
	return prodList;
}
public void setProdList(List<Product> prodList) {
	this.prodList = prodList;
}
public long getOrderTotal() {
	return orderTotal;
}
public void setOrderTotal(long orderTotal) {
	this.orderTotal = orderTotal;
}
}
