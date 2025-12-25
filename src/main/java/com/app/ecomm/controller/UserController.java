package com.app.ecomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.entity.Users;
import com.app.ecomm.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<Object> userHandler(@RequestHeader("Authorization") String token){
		Users user=userService.findUserByJwtToken(token);
		if(user==null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(user,HttpStatus.OK);
		
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<Object> userHandler(){
		List<Users> list=userService.findAllUsers();
	
		return new ResponseEntity(list,HttpStatus.OK);
		
	}

}
