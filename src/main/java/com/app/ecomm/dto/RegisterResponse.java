package com.app.ecomm.dto;

import com.app.ecomm.enums.USER_ROLE;

public class RegisterResponse {

	private String jwt;
	private String message;
	private USER_ROLE role;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public RegisterResponse(String jwt, String message, USER_ROLE role) {
		super();
		this.jwt = jwt;
		this.message = message;
		this.role = role;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public USER_ROLE getRole() {
		return role;
	}

	public void setRole(USER_ROLE role) {
		this.role = role;
	}

	public RegisterResponse(String jwt, USER_ROLE role) {
		super();
		this.jwt = jwt;
		this.role = role;
	}

	public RegisterResponse() {
		super();
	}

	@Override
	public String toString() {
		return "RegisterResponse [jwt=" + jwt + ", message=" + message + ", role=" + role + "]";
	}
	
	
	


}
