package com.app.ecomm.entity;

import java.util.Objects;

public class BusinessDetails {

	private String businessName;
	private String businessEmail;
	private String businessMobile;
	private String businessAddress;
	private String logo;
	private String banner;
	public BusinessDetails(String businessName, String businessEmail, String businessMobile, String businessAddress,
			String logo, String banner) {
		super();
		this.businessName = businessName;
		this.businessEmail = businessEmail;
		this.businessMobile = businessMobile;
		this.businessAddress = businessAddress;
		this.logo = logo;
		this.banner = banner;
	}
	public BusinessDetails() {
		super();
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessEmail() {
		return businessEmail;
	}
	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}
	public String getBusinessMobile() {
		return businessMobile;
	}
	public void setBusinessMobile(String businessMobile) {
		this.businessMobile = businessMobile;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	@Override
	public int hashCode() {
		return Objects.hash(banner, businessAddress, businessEmail, businessMobile, businessName, logo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessDetails other = (BusinessDetails) obj;
		return Objects.equals(banner, other.banner) && Objects.equals(businessAddress, other.businessAddress)
				&& Objects.equals(businessEmail, other.businessEmail)
				&& Objects.equals(businessMobile, other.businessMobile)
				&& Objects.equals(businessName, other.businessName) && Objects.equals(logo, other.logo);
	}
	@Override
	public String toString() {
		return "BusinessDetails [businessName=" + businessName + ", businessEmail=" + businessEmail
				+ ", businessMobile=" + businessMobile + ", businessAddress=" + businessAddress + ", logo=" + logo
				+ ", banner=" + banner + "]";
	}
	
	
}
