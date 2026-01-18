package com.app.ecomm.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.dto.PaymentLinkResponse;
import com.app.ecomm.entity.Address;
import com.app.ecomm.entity.Cart;
import com.app.ecomm.entity.Order;
import com.app.ecomm.entity.OrderItem;
import com.app.ecomm.entity.Seller;
import com.app.ecomm.entity.SellerReport;
import com.app.ecomm.entity.Users;
import com.app.ecomm.enums.PAYMENT_METHOD;
import com.app.ecomm.service.CartService;
import com.app.ecomm.service.OrderService;
import com.app.ecomm.service.SellerReportService;
import com.app.ecomm.service.SellerService;
import com.app.ecomm.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired 
	private OrderService orderService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private SellerReportService sellerReportService;
	
	@Autowired
	private SellerService sellerService;
	
	
	public ResponseEntity<PaymentLinkResponse> createOrder(
			@RequestBody Address address,
			@RequestParam PAYMENT_METHOD paymentMethod,
			Authentication auth
			){
		
		Users user=(Users)auth.getPrincipal();
		Cart cart=cartService.findUserCart(user).getBody();
		
		Set<Order> order=orderService.createOrder(user, address, cart);
		
		PaymentLinkResponse res=new PaymentLinkResponse();
		
		if(paymentMethod.equals(PAYMENT_METHOD.RAZORPAY)) {
			 
			
			
		}
		
		return new ResponseEntity(res, HttpStatus.OK);
		
		
	}
	
	public ResponseEntity<List<Order>> usersOrderHistory(Authentication auth){
		
		Users user=(Users)auth.getPrincipal();
		
		return new ResponseEntity(orderService.usersOrderHistory(user.getUserId()),HttpStatus.OK);
		
	}
	
	@GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) throws Exception{
		
		
		return new ResponseEntity(orderService.findOrderById(orderId) ,HttpStatus.OK);
		
	}
	
	@GetMapping("/item/{orderItemId}")
    public ResponseEntity<Order> getOrderItemById(@PathVariable Long orderItemId) throws Exception{
		
		
		return new ResponseEntity(orderService.findOrderItemById(orderItemId),HttpStatus.OK);
		
	}
	
	@PutMapping("/cancel/{orderid}")
	public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId,Authentication auth) throws Exception{
		
		Users user=(Users)auth.getPrincipal();
		
		Order order=orderService.cancelOrder(orderId, user);
		
		Seller seller =sellerService.getSellerById(order.getSellerId());
		
		SellerReport report=sellerReportService.getSellerReport(seller);
		
		report.setCanceledOrders(report.getCanceledOrders()+1);
		report.setTotalRefunds(report.getTotalRefunds()+order.getTotalSellingPrice());
		sellerReportService.updateSellerReport(report);
		
		return ResponseEntity.ok(orderService.cancelOrder(orderId, user));
		
	}
	
}
