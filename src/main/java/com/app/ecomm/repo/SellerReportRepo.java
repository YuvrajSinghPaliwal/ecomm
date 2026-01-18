package com.app.ecomm.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.Address;
import com.app.ecomm.entity.SellerReport;

public interface SellerReportRepo extends JpaRepository<SellerReport,Long>{

	
	Optional<SellerReport> findBySellerId(Long sellerId);
	
}
