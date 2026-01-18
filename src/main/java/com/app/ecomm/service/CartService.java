package com.app.ecomm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.ecomm.entity.Cart;
import com.app.ecomm.entity.CartItems;
import com.app.ecomm.entity.Product;
import com.app.ecomm.entity.Users;
import com.app.ecomm.repo.CartItemsRepo;
import com.app.ecomm.repo.CartRepo;

@Service
public class CartService {

	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private CartItemsRepo cartItemRepo;
	
	public ResponseEntity<CartItems> addCartItem(
	        Users user,
	        Product product,
	        String size,
	        int quantity) {

	    Cart cart = findUserCart(user).getBody();
	    if (cart == null) {
	        throw new RuntimeException("Cart not found");
	    }

	    Optional<CartItems> existingItem =
	            cartItemRepo.findByCartAndProduct(cart, product);

	    CartItems cartItem;

	    if (existingItem.isPresent()) {
	        cartItem = existingItem.get();
	        cartItem.setQuantity(cartItem.getQuantity() + quantity);
	    } else {
	        cartItem = new CartItems();
	        cartItem.setCart(cart);
	        cartItem.setProduct(product);
	       
	        cartItem.setQuantity(quantity);
	    }

	    cartItem.setSellingPrice(product.getSellingPrice() * cartItem.getQuantity());

	    return ResponseEntity.ok(cartItemRepo.save(cartItem));
	}


	public ResponseEntity<Cart> findUserCart(Users user) {

	    Cart cart = cartRepo.findByUserEmail(user.getEmail()).get();

	    // 🔥 MINIMAL FIX: create cart if not exists
	    if (cart == null) {
	        cart = new Cart();
	        cart.setUser(user);
	        cart = cartRepo.save(cart);
	    }

	    int totalPrice = 0;
	    int totalDiscountPrice = 0;
	    int totalItem = 0;

	    for (CartItems c : cart.getCartItems()) {
	        totalPrice += c.getMrpPrice();
	        totalDiscountPrice += c.getSellingPrice();
	        totalItem += c.getQuantity(); // 🔥 FIXED (was overwriting)
	    }

	    cart.setTotalMrpPrice(totalPrice);
	    cart.setTotalSellingPrice(totalDiscountPrice);
	    cart.setItems(totalItem);
	    cart.setDiscount(calculateTotalDiscount(totalPrice, totalDiscountPrice));

	    return new ResponseEntity<>(cart, HttpStatus.OK);
	}



	private double calculateTotalDiscount(int totalPrice, int totalDiscountPrice) {
		if(totalPrice==0) {
			return 0;
		}
		return ((totalPrice-totalDiscountPrice)/totalPrice)*100;
	}
	
}
