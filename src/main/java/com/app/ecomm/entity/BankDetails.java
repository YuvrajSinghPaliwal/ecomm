package com.app.ecomm.entity;

import java.util.Objects;

public class BankDetails {

	private String accountNumber;
	private String accountHolderName;
	private String ifscCode;
	 
	public BankDetails() {
		super();
	}

	public BankDetails(String accountNumber, String accountHolderName, String ifscCode) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.ifscCode = ifscCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	@Override
	public String toString() {
		return "BankDetails [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName
				+ ", ifscCode=" + ifscCode + "]";
	}
	
	
}
