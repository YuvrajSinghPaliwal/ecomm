package com.app.ecomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.entity.Order;
import com.app.ecomm.entity.Seller;
import com.app.ecomm.enums.ORDER_STATUS;
import com.app.ecomm.service.OrderService;
import com.app.ecomm.service.SellerReportService;
import com.app.ecomm.service.SellerService;

@RestController
@RequestMapping("/sellers/orders")
public class SellerOrderController {

	@Autowired 
	private SellerReportService sellerReportService;
	
	@Autowired 
	private SellerService sellerService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping()
	public ResponseEntity<List<Order>> getAllSellersOrder(Authentication auth)throws Exception{
		
		Seller seller=(Seller)auth.getPrincipal();
		
		List<Order> order=orderService.sellersOrders(seller.getId());
		
		return ResponseEntity.ok(order);
		
	}
	
	 @PatchMapping("/{id}/status/{orderStatus}")
	 public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
			 @PathVariable ORDER_STATUS orderStatus,Authentication auth ) throws Exception{
		 
		 Order order =orderService.updateOrderStatus(id, orderStatus);
		 
		 return ResponseEntity.ok(order);
		 
	 }
	 
	
	
}
