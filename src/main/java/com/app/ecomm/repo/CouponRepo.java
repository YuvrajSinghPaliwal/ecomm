package com.app.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.Address;
import com.app.ecomm.entity.Coupon;

public interface CouponRepo extends JpaRepository<Coupon,Long>{

}
