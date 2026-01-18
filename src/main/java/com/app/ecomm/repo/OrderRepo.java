package com.app.ecomm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.ecomm.entity.Address;
import com.app.ecomm.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
    
    // ✅ User (relationship → custom query)
    @Query("SELECT o FROM Order o WHERE o.user.userId = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);
    
    // ✅ Seller (direct field → simple query)
    List<Order> findBySellerId(Long sellerId);
    
    // ✅ Both work together!
}

