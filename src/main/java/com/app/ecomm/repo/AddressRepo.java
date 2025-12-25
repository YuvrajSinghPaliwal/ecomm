package com.app.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.Address;

public interface AddressRepo extends JpaRepository<Address,Long> {

	
}
