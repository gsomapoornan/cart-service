package com.visualpath.cartservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.visualpath.cartservice.model.OrderDetails;
import com.visualpath.cartservice.model.Orders;

public interface OrderDetailsCRUDRepository extends CrudRepository<OrderDetails, Long> {
	 public List<OrderDetails> findByOrderId(long orderId);
	   
}
