package com.visualpath.cartservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.visualpath.cartservice.model.UserCart;
import com.visualpath.cartservice.services.CartService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CartController {

	@Autowired
	CartService cartService;
	
	@RequestMapping(value = "/createOrder", method =RequestMethod.POST)
	public void addToCart(@RequestParam(value="userId",required=false) long userId,@RequestParam(value="productId",required=false) long productId) {
		 cartService.addToCart(userId, productId);		
	
	}
	@RequestMapping(value = "/showCart/{userId}", method =RequestMethod.GET)	
	public UserCart addToCart(@PathVariable(value="userId",required=false) long userId) {
		 return cartService.showCart(userId);		
	
	}
	
	
		

}
