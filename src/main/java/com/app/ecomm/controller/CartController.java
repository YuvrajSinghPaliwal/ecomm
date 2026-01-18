package com.app.ecomm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.dto.AddItemDto;
import com.app.ecomm.entity.Cart;
import com.app.ecomm.entity.CartItems;
import com.app.ecomm.entity.Product;
import com.app.ecomm.entity.Users;
import com.app.ecomm.repo.ProductRepo;
import com.app.ecomm.service.CartItemService;
import com.app.ecomm.service.CartService;

@RestController
@RequestMapping("/users/cart")
public class CartController {

	@Autowired 
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired 
	private ProductRepo productRepo;
	
	
	@GetMapping("/find/userCart")
	public ResponseEntity<Cart> findUserCart ( Authentication auth){
		
		Users user = (Users)auth.getPrincipal();
		
		return cartService.findUserCart(user);
		
	}
	
	@PostMapping("/item/add")
	public ResponseEntity<Object> addProductToCart(
			@RequestBody AddItemDto items,
			Authentication auth) throws Exception{
		
		Users user=(Users)auth.getPrincipal();
		
		Product product = productRepo.findById(items.getProductid())
		        .orElseThrow(() -> new RuntimeException("Product not found"));

		return new ResponseEntity(cartService.addCartItem(user, product, items.getSize(), items.getQuantity()),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/item/delete/{id}")
	public ResponseEntity<String> deleteCartItem(@PathVariable Long id,Authentication auth){
		
		Users user=(Users)auth.getPrincipal();
		
		return cartItemService.removeCartItem(user.getUserId(), id);
		
	}
	
	@PutMapping("/item/update/{id}")
	public ResponseEntity<CartItems> UpdateCartItem(@PathVariable Long id,
			@RequestBody CartItems cartItem,
			Authentication auth){
		
		Users user=(Users)auth.getPrincipal();
		
		return cartItemService.updateCartItems(user.getUserId(), id, cartItem);
		
	}
	
	
	
}
