package com.app.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.CartItems;

public interface CartItemsRepo extends JpaRepository<CartItems,Long>{

}
