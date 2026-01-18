package com.app.ecomm.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.ecomm.enums.ACCOUNT_STATUS;

import com.app.ecomm.enums.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Seller implements UserDetails {
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ✅ THIS IS MISSING!
    private Long id;
    
    private String sellerName, mobile, email, password, GSTIN;
    private USER_ROLE role = USER_ROLE.SELLER;
    private Boolean isEmailVerified = false;  // ✅ Wrapper Boolean
    private ACCOUNT_STATUS accountStatus = ACCOUNT_STATUS.PENDING_VERIFICATION;
    
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    @JsonIgnore
    @BatchSize(size = 50)  // 🔥 Batch fetch products
    private List<Product> products;
    
    // ✅ FIXED: NO DEFAULT INITIALIZATION
    @Embedded 
    private BusinessDetails businessDetails;
    
    @Embedded 
    private BankDetails bankDetails;
    
    @OneToOne(cascade=CascadeType.ALL)
    private Address pickupAddress;
    

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {  // ✅ Change from setUserId to setId
	    this.id = id;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BusinessDetails getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(BusinessDetails businessDetails) {
		this.businessDetails = businessDetails;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public Address getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(Address pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public String getGSTIN() {
		return GSTIN;
	}

	public void setGSTIN(String gSTIN) {
		GSTIN = gSTIN;
	}

	public USER_ROLE getRole() {
		return role;
	}

	public void setRole(USER_ROLE role) {
		this.role = role;
	}

	public Boolean isEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public ACCOUNT_STATUS getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(ACCOUNT_STATUS accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Seller(Long userId, String sellerName, String mobile, String email, String password,
			BusinessDetails businessDetails, BankDetails bankDetails, Address pickupAddress, String gSTIN,
			USER_ROLE role, Boolean isEmailVerified, ACCOUNT_STATUS accountStatus) {
		super();
		this.id = userId;
		this.sellerName = sellerName;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.businessDetails = businessDetails;
		this.bankDetails = bankDetails;
		this.pickupAddress = pickupAddress;
		GSTIN = gSTIN;
		this.role = role;
		this.isEmailVerified = isEmailVerified;
		this.accountStatus = accountStatus;
	}

	public Seller() {
		super();
	}

	@Override
	public String toString() {
		return "Seller [userId=" + id + ", sellerName=" + sellerName + ", mobile=" + mobile + ", email=" + email
				+ ", password=" + password + ", businessDetails=" + businessDetails + ", bankDetails=" + bankDetails
				+ ", pickupAddress=" + pickupAddress + ", GSTIN=" + GSTIN + ", role=" + role + ", isEmailVerified="
				+ isEmailVerified + ", accountStatus=" + accountStatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(GSTIN, accountStatus, bankDetails, businessDetails, email, isEmailVerified, mobile,
				password, pickupAddress, role, sellerName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(GSTIN, other.GSTIN) && accountStatus == other.accountStatus
				&& Objects.equals(bankDetails, other.bankDetails)
				&& Objects.equals(businessDetails, other.businessDetails) && Objects.equals(email, other.email)
				&& isEmailVerified == other.isEmailVerified && Objects.equals(mobile, other.mobile)
				&& Objects.equals(password, other.password) && Objects.equals(pickupAddress, other.pickupAddress)
				&& role == other.role && Objects.equals(sellerName, other.sellerName)
				&& Objects.equals(id, other.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+role.toString().toUpperCase()));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
