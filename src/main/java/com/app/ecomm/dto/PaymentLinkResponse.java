package com.app.ecomm.dto;

public class PaymentLinkResponse {

	private String paymentUrl;
	private String paymentId;
	public String getPaymentUrl() {
		return paymentUrl;
	}
	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public PaymentLinkResponse(String paymentUrl, String paymentId) {
		super();
		this.paymentUrl = paymentUrl;
		this.paymentId = paymentId;
	}
	public PaymentLinkResponse() {
		super();
	}
	@Override
	public String toString() {
		return "PaymentLinkResponse [paymentUrl=" + paymentUrl + ", paymentId=" + paymentId + "]";
	}
	
	
	
}
