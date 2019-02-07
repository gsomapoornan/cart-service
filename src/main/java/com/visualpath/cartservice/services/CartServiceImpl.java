package com.visualpath.cartservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.visualpath.cartservice.configuration.ProductConfiguration;
import com.visualpath.cartservice.configuration.UserConfiguration;
import com.visualpath.cartservice.model.OrderDetails;
import com.visualpath.cartservice.model.Orders;
import com.visualpath.cartservice.model.Product;
import com.visualpath.cartservice.model.User;
import com.visualpath.cartservice.model.UserCart;
import com.visualpath.cartservice.repository.OrderDetailsCRUDRepository;
import com.visualpath.cartservice.repository.OrdersCRUDRepository;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	OrdersCRUDRepository ordersRepo;
	@Autowired
	OrderDetailsCRUDRepository orderDetailsRepo;
	
	@Autowired
	UserRemoteCallService userRemoteClassService;
	
	@Autowired
	Orders orders;
	
	@Autowired
	OrderDetails orderDetails;
	
	@Autowired
	UserConfiguration userConfig;
	@Autowired
	ProductConfiguration prodConfig;
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	
	
	List<OrderDetails> orderList;
	
	List<Product> prodList=new ArrayList<Product>();
	@Autowired
	UserCart userCart;
	
	@Override
	public ResponseEntity < String > addToCart(long userId, long productId) {
		
	 ServiceInstance serviceInstance=this.discoveryClient.getInstances(prodConfig.getAppname()).get(0);
	 String url="http://" + serviceInstance.getHost()+ ":" + serviceInstance.getPort() + "/" + prodConfig.getProdIdresource();
		
	 RestTemplate restTemplate = new RestTemplate();	 
	 Product prod = restTemplate.getForObject(url,Product.class,productId);
	 
	//find if this user already has an order in cart and append to same order
	 if(ordersRepo.findByUserId(userId)!=null)
	 {	 
		orders=  ordersRepo.findByUserId(userId);
		orders.setUserId(userId);
		orders.setOrderTotal(orders.getOrderTotal()+prod.getPrice());
	 }
	 else
	 {
		 orders.setUserId(userId);
		 orders.setOrderTotal(prod.getPrice());
	 }
	        
	        
	        Orders savedOrder=ordersRepo.save(orders);
	          
	        
	        //Save Order Details
	        orderDetails=new OrderDetails();
	        orderDetails.setOrderId(savedOrder.getId());
	        orderDetails.setProductId(productId);
	        
	        OrderDetails savedOrderDetails=orderDetailsRepo.save(orderDetails);
	           if(savedOrderDetails!=null)
	           {
	        	   orders=null;
	        	  orderDetails=null;
	            return ResponseEntity.status(HttpStatus.CREATED).build();
	        }
	        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
	        
	        
	        
	}



	@Override
	public UserCart showCart(long userId) {
		
		 /*RestTemplate restTemplate = new RestTemplate();
		 User user = restTemplate.getForObject(userConfig.getUrl(),User.class,userId);
		     */
		User user =  userRemoteClassService.getUserByID(userId);
		
				if(ordersRepo.findByUserId(userId)!=null)
					orders=ordersRepo.findByUserId(userId);
				else
					return null;
		orderList=orderDetailsRepo.findByOrderId(orders.getId());
		if(orderList!=null)
		{
			prodList=new ArrayList<Product>();
			for(OrderDetails orders:orderList) {
				RestTemplate restTemplate1 = new RestTemplate();
				ServiceInstance serviceInstance=this.loadBalancer.choose(prodConfig.getAppname());
				 String url="http://" + serviceInstance.getHost()+ ":" + serviceInstance.getPort() + "/" + prodConfig.getProdIdresource();
					
				 Product prod = restTemplate1.getForObject(url,Product.class,orders.getProductId());
				prodList.add(prod);
			}
		}
		userCart.setId(userId);
		userCart.setName(user.getName());
		userCart.setAddress(user.getAddress());
		userCart.setProdList(prodList);
		userCart.setOrderTotal(orders.getOrderTotal());
		
		return userCart;
	}

	

}
