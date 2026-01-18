package com.app.ecomm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.ecomm.entity.Address;
import com.app.ecomm.entity.Seller;
import com.app.ecomm.enums.ACCOUNT_STATUS;
import com.app.ecomm.enums.USER_ROLE;
import com.app.ecomm.repo.AddressRepo;
import com.app.ecomm.repo.SellerRepo;

@Service
public class SellerService {

	@Autowired
	private SellerRepo sellerRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private JwtService jwtService;
	
	
	public List<Seller> getAllSellersWithStatus(ACCOUNT_STATUS status){
		return sellerRepo.findByAccountStatus(status);
	}
	
	public List<Seller> getAllSellers(){
		return sellerRepo.findAll();	}
	
	public ResponseEntity<Object> updateSeller(Seller seller) {
		Seller s=sellerRepo.findByEmail(seller.getEmail());
		if(s==null) {
			return new ResponseEntity("user not found",HttpStatus.BAD_REQUEST);
		}
		
		if(seller.getSellerName()!=null) {
			s.setSellerName(seller.getSellerName());
		}
		if(seller.getMobile()!=null) {
			s.setMobile(seller.getMobile());
		}
		if(seller.getEmail()!=null) {
			s.setEmail(seller.getEmail());
		}
		
		if(seller.getBankDetails()!=null
				) {
			s.setBankDetails(seller.getBankDetails());
		}
		if(seller.getBusinessDetails()!=null) {
			s.setBusinessDetails(seller.getBusinessDetails());
		}
		if(seller.getPickupAddress()!=null) {
			Address address=addressRepo.save(seller.getPickupAddress());
			s.setPickupAddress(address);
		}
		if(seller.getGSTIN()!=null) {
			s.setGSTIN(seller.getGSTIN());
		}
		
		
		Seller newSeller=sellerRepo.save(s);
		
		Map<String ,Object> body=new HashMap<>();
		body.put("jwt" ,jwtService.generateToken(seller.getEmail()));
		body.put("seller", newSeller);
		
		return new ResponseEntity(body,HttpStatus.OK);
		
	} 
	
	
	public ResponseEntity<Object> deleteSeller(String email){
		Seller s=sellerRepo.findByEmail(email);
		if(s==null) {
			return new ResponseEntity("user not found",HttpStatus.BAD_REQUEST);
		}
		sellerRepo.delete(s);
		return new ResponseEntity("deleted",HttpStatus.OK);
	}
	
	public Seller verifyEmail(String email,String otp) {
		
		Seller s=sellerRepo.findByEmail(email);
		
		s.setEmailVerified(true);
		
		return sellerRepo.save(s);
		
		
	}
	public ResponseEntity<Object> updateSellerAccountStatus(String email,ACCOUNT_STATUS status)throws Exception {
        Seller s=sellerRepo.findByEmail(email);
		
		s.setAccountStatus(status);
		
		return new ResponseEntity(sellerRepo.save(s),HttpStatus.OK);
	}

	public Seller getSellerById(Long sellerId) throws Exception {
		
		return sellerRepo.findById(sellerId).orElseThrow(()->new Exception("seller not found"));
		
	}
}
