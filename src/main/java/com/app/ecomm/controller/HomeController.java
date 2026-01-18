package com.app.ecomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.entity.Users;
import com.app.ecomm.repo.UserRepo;

@RestController
public class HomeController {

	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/")
	public String hello() {
		return "hello";
	}
	
		
}
