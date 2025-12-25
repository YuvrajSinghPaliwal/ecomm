package com.app.ecomm.dto;

import com.app.ecomm.entity.Seller;

public class SellersRegistrationDto {

	private Seller seller;
	
	private String otp;

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public SellersRegistrationDto(Seller seller, String otp) {
		super();
		this.seller = seller;
		this.otp = otp;
	}

	public SellersRegistrationDto() {
		super();
	}

	@Override
	public String toString() {
		return "SellersRegistrationDto [seller=" + seller + ", otp=" + otp + "]";
	}
	
	
	
}
