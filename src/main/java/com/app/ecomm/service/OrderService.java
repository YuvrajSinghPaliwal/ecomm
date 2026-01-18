package com.app.ecomm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.ecomm.entity.Address;
import com.app.ecomm.entity.Cart;
import com.app.ecomm.entity.CartItems;
import com.app.ecomm.entity.Order;
import com.app.ecomm.entity.OrderItem;
import com.app.ecomm.entity.Users;
import com.app.ecomm.enums.ORDER_STATUS;
import com.app.ecomm.enums.PAYMENT_STATUS;
import com.app.ecomm.repo.AddressRepo;
import com.app.ecomm.repo.OrderItemRepo;
import com.app.ecomm.repo.OrderRepo;
import com.app.ecomm.repo.UserRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private OrderItemRepo orderItemRepo;
	
	
	 public Set<Order> createOrder(Users user, Address address,Cart cart){
		 
		 if(!user.getAddresses().contains(address)) {
			 Address add=addressRepo.save(address);
			 user.getAddresses().add(add);
			 userService.updateUser(user.getUserId(), user);
		 }
		 
		 // for multiple sellers and product we create a map for sellers and their purchased items
		 
		 Map<Long,List<CartItems>> itemsBySellers=cart.getCartItems().stream()
				 .collect(Collectors.groupingBy(item->item.getProduct().getSeller().getId()));
		 
		 Set<Order> orders=new HashSet<>();
		 
		 for(Map.Entry<Long,List<CartItems>> entry:itemsBySellers.entrySet()) {
			
			 Long sellerId=entry.getKey();
			 List<CartItems> items=entry.getValue();
			 
			 int totalOrderPrice=items.stream().mapToInt(CartItems::getSellingPrice).sum();
			 
			 int totalQuantity =items.stream().mapToInt(CartItems::getQuantity).sum();
			 
			 Order createdOrder = new Order();
			 
			 createdOrder.setUser(user);
			 createdOrder.setSellerId(sellerId);
			 createdOrder.setTotalMrpPrice(totalOrderPrice);
			 createdOrder.setTotalSellingPrice(totalOrderPrice);
			 createdOrder.setTotalItems(totalQuantity);
			 createdOrder.setShippingAddress(address);
			 createdOrder.setOrderStatus(ORDER_STATUS.PENDING);
			 createdOrder.getPaymentDetails().setStatus(PAYMENT_STATUS.PENDING);
			 
			 Order savedOrder=orderRepo.save(createdOrder);
			 
			 orders.add(savedOrder);
			 
			 List<OrderItem> orderItems=new ArrayList<>();
			 
			 for(CartItems item:items) {
				 OrderItem orderItem=new OrderItem();
				 orderItem.setOrder(savedOrder);
				 orderItem.setSellingPrice(item.getSellingPrice());
				 orderItem.setProduct(item.getProduct());
				 orderItem.setQuantity(item.getQuantity());
				 orderItem.setPrice(item.getMrpPrice());
				 orderItem.setUserId(item.getUserId());
				 orderItem.setSize(item.getSize()+"");
				 
				 savedOrder.getOrderItems().add(orderItem);
				 
				 OrderItem savedOrderItem=orderItemRepo.save(orderItem);
				 orderItems.add(savedOrderItem);
				 
			 }
			 
			 
		 }
		 return orders;
		 
	 }
	
	 public Order findOrderById(Long id) throws Exception {
		 
		 Optional<Order> orderss=orderRepo.findById(id);
		 
		 if(orderss.isPresent()) {
			 return orderss.get();
		 }
		 
		 throw new Exception("order not present");
		 
	 }
	 
	 public List<Order> usersOrderHistory(Long id){
		 return orderRepo.findByUserId(id);
	 }
	 
	 public List<Order> sellersOrders(Long id){
		 return orderRepo.findBySellerId(id);
	 }
	 
	 public Order updateOrderStatus(Long id,ORDER_STATUS orderStatus) throws Exception{
		 
		 Optional<Order> order=orderRepo.findById(id);
		 if(order.isPresent()) {
			 Order o=order.get();
			 o.setOrderStatus(orderStatus);
			 return orderRepo.save(o);
		 }
		 throw new Exception("order not present");
	 }
	 
     public Order cancelOrder(Long id,Users user) throws Exception{
		 
    	 
    	 
		 Optional<Order> order=orderRepo.findById(id);
		 
		 if(order.isPresent()) {
			 Order o=order.get();
			 if(!(user.getUserId()==o.getUser().getUserId())) {
				 throw new Exception("not authorised for this action");
			 }
			 else {
				 o.setOrderStatus(ORDER_STATUS.CANCELLED);
				 return orderRepo.save(o);
			 }
		 }
		 throw new Exception("order not present");
	 }
	 
     
     public OrderItem findOrderItemById(Long id) throws Exception {
		 
		 Optional<OrderItem> orderss=orderItemRepo.findById(id);
		 
		 if(orderss.isPresent()) {
			 return orderss.get();
		 }
		 
		 throw new Exception("order not present");
		 
	 }
	 
	 
}
