package com.app.ecomm.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.Cart;

public interface CartRepo extends JpaRepository<Cart,Long>{

	Optional<Cart> findByUserEmail(String email);

}
