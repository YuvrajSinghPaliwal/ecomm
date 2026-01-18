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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") 
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ✅ Changed from AUTO
    private Long userId;

    private String username;
    private String email;
    private String password;
    private String mobile;

    @Enumerated(EnumType.STRING)
    private USER_ROLE role = USER_ROLE.CUSTOMER;

    // 🔥 FIXED: Proper bidirectional relationships
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Address> addresses = new HashSet<>();
    
    @ManyToMany
    @JsonIgnore  
    private Set<Coupon> usedCoupons = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();  // 🔥 ADD THIS
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Cart cart; 
    
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
	        if (role == null) {
	            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
	        }
	        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name().toUpperCase()));
	    }

	    @Override
	    public String getUsername() {
	        return email; // 🔥 CRITICAL: JWT uses EMAIL!
	    }

	    @Override
	    public String getPassword() {
	        return password;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true; // 🔥 MISSING METHOD!
	    }
	}