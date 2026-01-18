package com.app.ecomm.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ecomm.entity.Address;
import com.app.ecomm.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Long>{
 
	
	
	Optional<Category> findByName(String name); 
}
