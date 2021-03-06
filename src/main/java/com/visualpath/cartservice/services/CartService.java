package com.visualpath.cartservice.services;

import org.springframework.http.ResponseEntity;

import com.visualpath.cartservice.model.UserCart;

public interface CartService {
	
	public ResponseEntity<String> addToCart(long userId,long productId);

	public UserCart showCart(long userId);

}
