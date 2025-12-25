package com.app.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.Address;
import com.app.ecomm.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem,Long>{

}
