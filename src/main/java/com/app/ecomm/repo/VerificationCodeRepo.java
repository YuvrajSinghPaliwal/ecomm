package com.app.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.VerificationCode;

public interface VerificationCodeRepo extends JpaRepository<VerificationCode,Long>{

	VerificationCode findByEmail(String email);
	
}
