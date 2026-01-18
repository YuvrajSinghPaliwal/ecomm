package com.app.ecomm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.ecomm.entity.CartItems;
import com.app.ecomm.entity.Users;
import com.app.ecomm.repo.CartItemsRepo;
import com.app.ecomm.repo.CartRepo;

@Service
public class CartItemService {

	@Autowired
	private CartItemsRepo cartItemRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	public ResponseEntity<CartItems> updateCartItems(Long userId,Long id,CartItems cartItem) {
		
		CartItems c=cartItemRepo.findById(id).get();
		
		Users user=cartItem.getCart().getUser();
		
		if(user.getUserId()==userId) {
			c.setQuantity(cartItem.getQuantity());
			c.setMrpPrice(cartItem.getMrpPrice());
			c.setSellingPrice(cartItem.getSellingPrice());
			
			return new ResponseEntity(cartItemRepo.save(c),HttpStatus.OK);
		}
		
		return new ResponseEntity("not authorised for this action",HttpStatus.BAD_REQUEST);
		
	}
	
	public ResponseEntity<String> removeCartItem(Long userId,Long id) {
		
		CartItems c=cartItemRepo.findById(id).get();
		
		Users user=c.getCart().getUser();
		
		if(user.getUserId()==userId) {
			
			cartItemRepo.delete(c);
			return new ResponseEntity("deleted sucessfully",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity("not authorised for this action",HttpStatus.BAD_REQUEST);
	}
	
}
