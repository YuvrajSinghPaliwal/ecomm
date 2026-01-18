package com.app.ecomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.entity.Seller;
import com.app.ecomm.entity.SellerReport;
import com.app.ecomm.entity.VerificationCode;
import com.app.ecomm.enums.ACCOUNT_STATUS;
import com.app.ecomm.service.OtpService;
import com.app.ecomm.service.SellerReportService;
import com.app.ecomm.service.SellerService;

@RestController
@RequestMapping("/sellers")
public class SellerController {

	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private SellerReportService sellerReportService;

	@PutMapping("/update")  // ✅ NOT "/update"
	public ResponseEntity<Object> updateSeller(@RequestBody Seller seller) {
	    return sellerService.updateSeller(seller);
	}

	
	@PutMapping("/updateUserAccountStatus")
	public ResponseEntity<Object> updateSellerAccountStatus(@RequestParam String email,@RequestParam ACCOUNT_STATUS status) throws Exception{
		return sellerService.updateSellerAccountStatus(email,status);
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<Object> deleteSeller(@PathVariable String email){
		return sellerService.deleteSeller(email);
	}
	
	@GetMapping("/")
	public List<Seller> listSellers(){
		return sellerService.getAllSellers();
	}
	
	@GetMapping("/report")
	public SellerReport getSellerReport(Authentication auth) throws Exception {
		
		Seller seller=(Seller)auth.getPrincipal();
		
		return sellerReportService.getSellerReport(seller);
	}
	
}
