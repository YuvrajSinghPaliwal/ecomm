package com.app.ecomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.entity.Product;
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
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable Long id,@RequestBody Users user){
		return userService.updateUser(id,user);
	}

}
