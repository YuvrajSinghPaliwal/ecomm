package com.app.ecomm.entity;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String code;
	
	private double discount;
	
	private LocalDate validityStartDate;
	
	private LocalDate validityEndDate;
	
	private double minOrderVlaue;
	
	private boolean isActive=true;
	

	
	@ManyToMany(mappedBy="usedCoupons")
	private Set<Users> users=new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public LocalDate getValidityStartDate() {
		return validityStartDate;
	}

	public void setValidityStartDate(LocalDate validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	public LocalDate getValidityEndDate() {
		return validityEndDate;
	}

	public void setValidityEndDate(LocalDate validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	public double getMinOrderVlaue() {
		return minOrderVlaue;
	}

	public void setMinOrderVlaue(double minOrderVlaue) {
		this.minOrderVlaue = minOrderVlaue;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public Coupon(Long id, String code, double discount, LocalDate validityStartDate, LocalDate validityEndDate,
			double minOrderVlaue, boolean isActive, Set<Users> users) {
		super();
		this.id = id;
		this.code = code;
		this.discount = discount;
		this.validityStartDate = validityStartDate;
		this.validityEndDate = validityEndDate;
		this.minOrderVlaue = minOrderVlaue;
		this.isActive = isActive;
		this.users = users;
	}

	public Coupon() {
		super();
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", code=" + code + ", discount=" + discount + ", validityStartDate="
				+ validityStartDate + ", validityEndDate=" + validityEndDate + ", minOrderVlaue=" + minOrderVlaue
				+ ", isActive=" + isActive + ", users=" + users + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, discount, id, isActive, minOrderVlaue, users, validityEndDate, validityStartDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		return Objects.equals(code, other.code)
				&& Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
				&& Objects.equals(id, other.id) && isActive == other.isActive
				&& Double.doubleToLongBits(minOrderVlaue) == Double.doubleToLongBits(other.minOrderVlaue)
				&& Objects.equals(users, other.users) && Objects.equals(validityEndDate, other.validityEndDate)
				&& Objects.equals(validityStartDate, other.validityStartDate);
	}
	
	
}
