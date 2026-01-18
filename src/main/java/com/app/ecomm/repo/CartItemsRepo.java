package com.app.ecomm.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.Cart;
import com.app.ecomm.entity.CartItems;
import com.app.ecomm.entity.Product;

public interface CartItemsRepo extends JpaRepository<CartItems,Long>{

	Optional<CartItems> findByCartAndProduct(Cart cart, Product product);
	

}
