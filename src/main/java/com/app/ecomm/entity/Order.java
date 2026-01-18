package com.app.ecomm.entity;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.app.ecomm.enums.ORDER_STATUS;
import com.app.ecomm.enums.PAYMENT_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders") 
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String orderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_user_id")
	private Users user; 
	
	private Long sellerId;
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<OrderItem> orderItems=new ArrayList<>();
	
	@ManyToOne
	private Address shippingAddress;
	
	@Embedded
	private PaymentDetails paymentDetails=new PaymentDetails();
	
	private double totalMrpPrice;
	
	private int totalSellingPrice;
	
	private int discount;
	
	private ORDER_STATUS orderStatus=ORDER_STATUS.PENDING;
	
	private int totalItems;
	
	private PAYMENT_STATUS paymentStatus=PAYMENT_STATUS.PENDING;
	
	private LocalDateTime orderDate=LocalDateTime.now();
	
	private LocalDateTime deliveredDate=orderDate.plusDays(7);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public double getTotalMrpPrice() {
		return totalMrpPrice;
	}

	public void setTotalMrpPrice(double totalMrpPrice) {
		this.totalMrpPrice = totalMrpPrice;
	}

	public int getTotalSellingPrice() {
		return totalSellingPrice;
	}

	public void setTotalSellingPrice(int totalSellingPrice) {
		this.totalSellingPrice = totalSellingPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public ORDER_STATUS getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(ORDER_STATUS orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public PAYMENT_STATUS getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PAYMENT_STATUS paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(LocalDateTime deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public Order(Long id, String orderId, Users user, Long sellerId, List<OrderItem> orderItems,
			Address shippingAddress, PaymentDetails paymentDetails, double totalMrpPrice, int totalSellingPrice,
			int discount, ORDER_STATUS orderStatus, int totalItems, PAYMENT_STATUS paymentStatus,
			LocalDateTime orderDate, LocalDateTime deliveredDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.user = user;
		this.sellerId = sellerId;
		this.orderItems = orderItems;
		this.shippingAddress = shippingAddress;
		this.paymentDetails = paymentDetails;
		this.totalMrpPrice = totalMrpPrice;
		this.totalSellingPrice = totalSellingPrice;
		this.discount = discount;
		this.orderStatus = orderStatus;
		this.totalItems = totalItems;
		this.paymentStatus = paymentStatus;
		this.orderDate = orderDate;
		this.deliveredDate = deliveredDate;
	}

	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderId=" + orderId + ", user=" + user + ", sellerId=" + sellerId
				+ ", orderItems=" + orderItems + ", shippingAddress=" + shippingAddress + ", paymentDetails="
				+ paymentDetails + ", totalMrpPrice=" + totalMrpPrice + ", totalSellingPrice=" + totalSellingPrice
				+ ", discount=" + discount + ", orderStatus=" + orderStatus + ", totalItems=" + totalItems
				+ ", paymentStatus=" + paymentStatus + ", orderDate=" + orderDate + ", deliveredDate=" + deliveredDate
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(deliveredDate, discount, id, orderDate, orderId, orderItems, orderStatus, paymentDetails,
				paymentStatus, sellerId, shippingAddress, totalItems, totalMrpPrice, totalSellingPrice, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(deliveredDate, other.deliveredDate) && discount == other.discount
				&& Objects.equals(id, other.id) && Objects.equals(orderDate, other.orderDate)
				&& Objects.equals(orderId, other.orderId) && Objects.equals(orderItems, other.orderItems)
				&& orderStatus == other.orderStatus && Objects.equals(paymentDetails, other.paymentDetails)
				&& paymentStatus == other.paymentStatus && Objects.equals(sellerId, other.sellerId)
				&& Objects.equals(shippingAddress, other.shippingAddress) && totalItems == other.totalItems
				&& Double.doubleToLongBits(totalMrpPrice) == Double.doubleToLongBits(other.totalMrpPrice)
				&& totalSellingPrice == other.totalSellingPrice && Objects.equals(user, other.user);
	}
	
	
}
