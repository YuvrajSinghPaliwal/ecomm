package com.app.ecomm.entity;

import java.util.Objects;

import com.app.ecomm.enums.PAYMENT_STATUS;




public class PaymentDetails {

	private String paymentId;
	private String razorpayPaymentLinkId;
	private String razorpayPaymentLinkReferenceId;
	private String razorpayPaymentLinkStatus;
	private String razorpayPaymentId;
	private PAYMENT_STATUS status;
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getRazorpayPaymentLinkId() {
		return razorpayPaymentLinkId;
	}
	public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
	}
	public String getRazorpayPaymentLinkReferenceId() {
		return razorpayPaymentLinkReferenceId;
	}
	public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
	}
	public String getRazorpayPaymentLinkStatus() {
		return razorpayPaymentLinkStatus;
	}
	public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
	}
	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}
	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}
	public PAYMENT_STATUS getStatus() {
		return status;
	}
	public void setStatus(PAYMENT_STATUS status) {
		this.status = status;
	}
	public PaymentDetails(String paymentId, String razorpayPaymentLinkId, String razorpayPaymentLinkReferenceId,
			String razorpayPaymentLinkStatus, String razorpayPaymentId, PAYMENT_STATUS status) {
		super();
		this.paymentId = paymentId;
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
		this.razorpayPaymentId = razorpayPaymentId;
		this.status = status;
	}
	public PaymentDetails() {
		super();
	}
	@Override
	public String toString() {
		return "PaymentDetails [paymentId=" + paymentId + ", razorpayPaymentLinkId=" + razorpayPaymentLinkId
				+ ", razorpayPaymentLinkReferenceId=" + razorpayPaymentLinkReferenceId + ", razorpayPaymentLinkStatus="
				+ razorpayPaymentLinkStatus + ", razorpayPaymentId=" + razorpayPaymentId + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(paymentId, razorpayPaymentId, razorpayPaymentLinkId, razorpayPaymentLinkReferenceId,
				razorpayPaymentLinkStatus, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentDetails other = (PaymentDetails) obj;
		return Objects.equals(paymentId, other.paymentId) && Objects.equals(razorpayPaymentId, other.razorpayPaymentId)
				&& Objects.equals(razorpayPaymentLinkId, other.razorpayPaymentLinkId)
				&& Objects.equals(razorpayPaymentLinkReferenceId, other.razorpayPaymentLinkReferenceId)
				&& Objects.equals(razorpayPaymentLinkStatus, other.razorpayPaymentLinkStatus) && status == other.status;
	}
	
	
}
