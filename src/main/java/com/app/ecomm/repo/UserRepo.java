package com.app.ecomm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.Users;

public interface UserRepo extends JpaRepository<Users,Long>{

	Users findByEmail(String email);
}
