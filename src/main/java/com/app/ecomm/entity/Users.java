package com.app.ecomm.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.ecomm.enums.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") 
public class Users implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	private String username;
	
	private String email;
	private String password;
	
	private String mobile;
	
	private USER_ROLE role=USER_ROLE.ROLE_CUSTOMER;
	
	@OneToMany
	private Set<Address> addresses =new HashSet<>();
	
	@ManyToMany
	@JsonIgnore
	private Set<Coupon> usedCoupons =new HashSet<>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public USER_ROLE getRole() {
		return role;
	}

	public void setRole(USER_ROLE role) {
		this.role = role;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Coupon> getUsedCoupons() {
		return usedCoupons;
	}

	public void setUsedCoupons(Set<Coupon> usedCoupons) {
		this.usedCoupons = usedCoupons;
	}

	public Users(Long userId, String username, String email, String password, String mobile, USER_ROLE role,
			Set<Address> addresses, Set<Coupon> usedCoupons) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.role = role;
		this.addresses = addresses;
		this.usedCoupons = usedCoupons;
	}

	public Users() {
		super();
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", mobile=" + mobile + ", role=" + role + ", addresses=" + addresses + ", usedCoupons=" + usedCoupons
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(addresses, email, mobile, password, role, usedCoupons, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return Objects.equals(addresses, other.addresses) && Objects.equals(email, other.email)
				&& Objects.equals(mobile, other.mobile) && Objects.equals(password, other.password)
				&& role == other.role && Objects.equals(usedCoupons, other.usedCoupons)
				&& Objects.equals(userId, other.userId) && Objects.equals(username, other.username);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority(role.toString()));
	}
	
	
	
}
