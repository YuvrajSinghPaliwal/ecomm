package com.app.ecomm.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.app.ecomm.enums.PAYMENT_METHOD;
import com.app.ecomm.enums.PAYMENT_ORDER_STATUS;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class PaymentOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int amount;
	
	private PAYMENT_ORDER_STATUS paymentOrderStatus=PAYMENT_ORDER_STATUS.PENDING;
	
	private PAYMENT_METHOD paymentMethod;
	
	private String paymentLinkedId;
	
	@ManyToOne
	private Users user;
	
	@OneToMany
	private Set<Order> orders=new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int i) {
		this.amount = i;
	}

	public PAYMENT_ORDER_STATUS getPaymentOrderStatus() {
		return paymentOrderStatus;
	}

	public void setPaymentOrderStatus(PAYMENT_ORDER_STATUS paymentOrderStatus) {
		this.paymentOrderStatus = paymentOrderStatus;
	}

	public PAYMENT_METHOD getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PAYMENT_METHOD paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentLinkedId() {
		return paymentLinkedId;
	}

	public void setPaymentLinkedId(String paymentLinkedId) {
		this.paymentLinkedId = paymentLinkedId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public PaymentOrder(Long id, int amount, PAYMENT_ORDER_STATUS paymentOrderStatus, PAYMENT_METHOD paymentMethod,
			String paymentLinkedId, Users user, Set<Order> orders) {
		super();
		this.id = id;
		this.amount = amount;
		this.paymentOrderStatus = paymentOrderStatus;
		this.paymentMethod = paymentMethod;
		this.paymentLinkedId = paymentLinkedId;
		this.user = user;
		this.orders = orders;
	}

	public PaymentOrder() {
		super();
	}

	@Override
	public String toString() {
		return "PaymentOrder [id=" + id + ", amount=" + amount + ", paymentOrderStatus=" + paymentOrderStatus
				+ ", paymentMethod=" + paymentMethod + ", paymentLinkedId=" + paymentLinkedId + ", user=" + user
				+ ", orders=" + orders + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, id, orders, paymentLinkedId, paymentMethod, paymentOrderStatus, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentOrder other = (PaymentOrder) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(id, other.id)
				&& Objects.equals(orders, other.orders) && Objects.equals(paymentLinkedId, other.paymentLinkedId)
				&& paymentMethod == other.paymentMethod && paymentOrderStatus == other.paymentOrderStatus
				&& Objects.equals(user, other.user);
	}
	
	 
}
