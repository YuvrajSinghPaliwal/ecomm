package com.app.ecomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.dto.LoginDto;
import com.app.ecomm.dto.RegisterResponse;
import com.app.ecomm.dto.SellersRegistrationDto;
import com.app.ecomm.dto.UsersRegistrationDto;
import com.app.ecomm.entity.Seller;
import com.app.ecomm.entity.Users;
import com.app.ecomm.entity.VerificationCode;
import com.app.ecomm.repo.UserRepo;
import com.app.ecomm.service.AuthService;
import com.app.ecomm.service.OtpService;
import com.app.ecomm.service.SellerService;


@RestController
@RequestMapping("/public")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private OtpService otpService;
	

	@PostMapping("/user/register")
	public ResponseEntity<RegisterResponse> registerUser(@RequestBody UsersRegistrationDto userDto) throws Exception{
		return authService.registerUser(userDto);

	}
	
	@PostMapping("/user/login")
	public ResponseEntity<Object> userLogin(@RequestBody LoginDto loginDto){
		return authService.userLogin(loginDto);
	}
	
	@PostMapping("/sent/login-signup-otp")
	public ResponseEntity<?> getOtp(@RequestBody VerificationCode req) throws Exception{
		 otpService.sentLoginOtp(req.getEmail());
		 return new ResponseEntity(HttpStatus.OK);

	}
	
	@PostMapping("/seller/register")
	public ResponseEntity<Object> registerSeller(@RequestBody SellersRegistrationDto sellerDto) throws Exception{
		return authService.createSeller(sellerDto);

	}
	
	@PostMapping("/seller/login")
	public ResponseEntity<Object> sellerLogin(@RequestBody LoginDto loginDto){
		return authService.sellerLogin(loginDto);
	}
	
}
