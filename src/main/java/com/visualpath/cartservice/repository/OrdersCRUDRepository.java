package com.visualpath.cartservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.visualpath.cartservice.model.Orders;

public interface OrdersCRUDRepository extends CrudRepository<Orders, Long> {	
	   
	 public Orders findByUserId(long userId);
	  
}
