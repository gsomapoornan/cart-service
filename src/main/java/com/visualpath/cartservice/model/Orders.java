package com.visualpath.cartservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Entity
@XmlRootElement
@Component
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	long userId;
	long orderTotal;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(long orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	

}
