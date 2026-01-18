package com.app.ecomm.entity;

import java.util.Objects;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String locality;
	
	private String address;
	
	private String city;
	
	private String district;
	
	private String state;
	
	private String pincode;
	
	private String mobile;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_user_id")  
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, city, district, id, locality, mobile, name, pincode, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(district, other.district) && Objects.equals(id, other.id)
				&& Objects.equals(locality, other.locality) && Objects.equals(mobile, other.mobile)
				&& Objects.equals(name, other.name) && Objects.equals(pincode, other.pincode)
				&& Objects.equals(state, other.state);
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", locality=" + locality + ", address=" + address + ", city="
				+ city + ", district=" + district + ", state=" + state + ", pincode=" + pincode + ", mobile=" + mobile
				+ "]";
	}

	public Address(Long id, String name, String locality, String address, String city, String district, String state,
			String pincode, String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.locality = locality;
		this.address = address;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
		this.mobile = mobile;
	}

	public Address(String name, String locality, String address, String city, String district, String state,
			String pincode, String mobile) {
		super();
		this.name = name;
		this.locality = locality;
		this.address = address;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
		this.mobile = mobile;
	}

	public Address() {
		super();
	}
	
	
	
	
}
