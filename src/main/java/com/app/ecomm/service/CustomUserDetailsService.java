package com.app.ecomm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.ecomm.entity.Seller;
import com.app.ecomm.entity.Users;
import com.app.ecomm.repo.SellerRepo;
import com.app.ecomm.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private SellerRepo sellerRepo;
	
	public static final String SELLER_PREFIX="seller_";
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(username.startsWith(SELLER_PREFIX)) {
			String email=username.substring(SELLER_PREFIX.length());
			Seller seller=sellerRepo.findByEmail(email);
			if(seller!=null) {
				return seller;
			}
			
			
		}
		else {
			Users user=userRepo.findByEmail(username);
			if(user!=null) {
				return user;
			}
		}
		throw new UsernameNotFoundException("User not found");
		
	}

}
