package com.app.ecomm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.ecomm.entity.Product;
import com.app.ecomm.entity.Users;
import com.app.ecomm.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtService jwtService;
	
	public Users findUserByJwtToken(String token) {
		String email=jwtService.extractUsername(token);
		if(email!=null) {
			Users user=userRepo.findByEmail(email);
			return user;
		}
		return null;
	}
	
	public List<Users> findAllUsers() {
		List<Users> list=userRepo.findAll();
		
		return list;
	}
	
	@Transactional
	public ResponseEntity<String> deleteUser(Long userId) {
	    Optional<Users> userOpt = userRepo.findById(userId);
	    if (userOpt.isEmpty()) {
	        return ResponseEntity.badRequest().body("User not found");
	    }
	    
	    // Cascade handles addresses, coupons, orders automatically
	    userRepo.deleteById(userId);
	    return ResponseEntity.ok("User deleted successfully");
	}
	
	
    public ResponseEntity<Users> updateUser(Long userId,Users user){
		userRepo.findById(userId);
		user.setUserId(userId);
		return new ResponseEntity<Users>(userRepo.save(user),HttpStatus.OK);
	}
	
}
