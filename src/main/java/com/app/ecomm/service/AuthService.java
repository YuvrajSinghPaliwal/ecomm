package com.app.ecomm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.ecomm.dto.LoginDto;
import com.app.ecomm.dto.RegisterResponse;
import com.app.ecomm.dto.SellersRegistrationDto;
import com.app.ecomm.dto.UsersRegistrationDto;
import com.app.ecomm.entity.Address;
import com.app.ecomm.entity.Cart;
import com.app.ecomm.entity.Seller;
import com.app.ecomm.entity.Users;
import com.app.ecomm.entity.VerificationCode;
import com.app.ecomm.enums.ACCOUNT_STATUS;
import com.app.ecomm.enums.USER_ROLE;
import com.app.ecomm.repo.AddressRepo;
import com.app.ecomm.repo.CartRepo;
import com.app.ecomm.repo.SellerRepo;
import com.app.ecomm.repo.UserRepo;
import com.app.ecomm.repo.VerificationCodeRepo;

@Service
public class AuthService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private VerificationCodeRepo verificationCodeRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired 
	private SellerRepo sellerRepo;
	
	public ResponseEntity<RegisterResponse> registerUser(UsersRegistrationDto userDto)throws Exception{
		
		VerificationCode verificationCode=verificationCodeRepo.findByEmail(userDto.getEmail());
		
		if(verificationCode==null|| !verificationCode.getOtp().equals(userDto.getOtp())) {
			throw new Exception("invalid");
		}
		
		Users user = userRepo.findByEmail(userDto.getEmail());
		if(user==null) {
			Users addUser=new Users();
			addUser.setUsername(userDto.getName());
			addUser.setMobile(userDto.getPhone());
			addUser.setEmail(userDto.getEmail());
			addUser.setPassword(encoder.encode(userDto.getPassword()));
			addUser.setRole(USER_ROLE.CUSTOMER);
			Users users=userRepo.save(addUser);
			Cart cart=new Cart();
			cart.setUser(users);
			cartRepo.save(cart);
			
			Authentication auth=new UsernamePasswordAuthenticationToken(users,null,users.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			RegisterResponse res=new RegisterResponse();
			res.setJwt(jwtService.generateToken(users.getEmail()));
			res.setMessage("");
			res.setRole(addUser.getRole());
			
			return new ResponseEntity<>(res,HttpStatus.CREATED);
			
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Object> userLogin(LoginDto loginDto){
		Users user=userRepo.findByEmail(loginDto.getEmail());
		if(user!=null && encoder.matches(loginDto.getPassword(), user.getPassword())) {
			Map<String,Object> body=new HashMap<>();
			body.put("jwt", jwtService.generateToken(loginDto.getEmail()));
			body.put("user", user);
			return new ResponseEntity(body,HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Object> createSeller(SellersRegistrationDto sellerDto) throws Exception {
	    Seller sellerData = sellerDto.getSeller();
	    
	    // OTP verification
	    VerificationCode verificationCode = verificationCodeRepo.findByEmail(sellerData.getEmail());
	    if (verificationCode == null || !verificationCode.getOtp().equals(sellerDto.getOtp())) {
	        return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
	    }
	    
	    if (sellerRepo.findByEmail(sellerData.getEmail()) != null) {
	        return new ResponseEntity<>("Seller already exists", HttpStatus.BAD_REQUEST);
	    }
	    
	    // ✅ Create and populate seller
	    Seller s = new Seller();
	    s.setSellerName(sellerData.getSellerName());
	    s.setEmail(sellerData.getEmail());
	    s.setMobile(sellerData.getMobile());
	    s.setPassword(encoder.encode(sellerData.getPassword()));
	    s.setGSTIN(sellerData.getGSTIN());
	    s.setRole(sellerData.getRole());
	    
	    // ✅ SAFE null checks for embedded objects
	    if (sellerData.getPickupAddress() != null) {
	        Address address = new Address();
	        address.setName(sellerData.getPickupAddress().getName());
	        address.setLocality(sellerData.getPickupAddress().getLocality());
	        address.setAddress(sellerData.getPickupAddress().getAddress());
	        address.setCity(sellerData.getPickupAddress().getCity());
	        address.setDistrict(sellerData.getPickupAddress().getDistrict());
	        address.setState(sellerData.getPickupAddress().getState());
	        address.setPincode(sellerData.getPickupAddress().getPincode());
	        address.setMobile(sellerData.getPickupAddress().getMobile());
	        s.setPickupAddress(addressRepo.save(address));
	    }
	    
	    s.setBusinessDetails(sellerData.getBusinessDetails());
	    s.setBankDetails(sellerData.getBankDetails());
	    s.setEmailVerified(true);
	    
	    Seller newSeller = sellerRepo.save(s);
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("jwt", jwtService.generateToken("seller_"+newSeller.getEmail()));
	    response.put("seller", newSeller);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}



	
	
	public ResponseEntity<Object> sellerLogin(LoginDto loginDto){
		Seller seller=sellerRepo.findByEmail(loginDto.getEmail());
		if(seller!=null && encoder.matches(loginDto.getPassword(), seller.getPassword())) {
			Map<String,Object> body=new HashMap<>();
			body.put("jwt", jwtService.generateToken("seller_"+loginDto.getEmail()));
			body.put("seller", seller);
			return new ResponseEntity(body,HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
}
