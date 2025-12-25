package com.app.ecomm.entity;

import java.util.Objects;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class VerificationCode {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	private String otp;
	
	private String email;
	
	@OneToOne
	private Users user;
	
	@OneToOne
	private Seller seller;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public VerificationCode(Long userId, String otp, String email, Users user, Seller seller) {
		super();
		this.userId = userId;
		this.otp = otp;
		this.email = email;
		this.user = user;
		this.seller = seller;
	}

	public VerificationCode() {
		super();
	}

	@Override
	public String toString() {
		return "VerificationCode [userId=" + userId + ", otp=" + otp + ", email=" + email + ", user=" + user
				+ ", seller=" + seller + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, otp, seller, user, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerificationCode other = (VerificationCode) obj;
		return Objects.equals(email, other.email) && Objects.equals(otp, other.otp)
				&& Objects.equals(seller, other.seller) && Objects.equals(user, other.user)
				&& Objects.equals(userId, other.userId);
	}
	
	
}
