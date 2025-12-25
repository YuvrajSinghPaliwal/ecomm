package com.app.ecomm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.Seller;
import com.app.ecomm.enums.ACCOUNT_STATUS;

public interface SellerRepo extends JpaRepository<Seller,Long> {

	Seller findByEmail(String email);
	List<Seller> findByAccountStatus(ACCOUNT_STATUS status);
}
