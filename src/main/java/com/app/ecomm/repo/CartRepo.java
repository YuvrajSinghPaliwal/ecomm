package com.app.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.Cart;

public interface CartRepo extends JpaRepository<Cart,Long>{

}
