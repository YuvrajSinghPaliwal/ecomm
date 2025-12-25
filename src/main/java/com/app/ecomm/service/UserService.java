package com.app.ecomm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
