package com.app.ecomm.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	@ManyToOne
	private Users customer;
	
	@OneToOne
	private Order order;
	
	@ManyToOne
	private Seller seller;
	
	private int amount;
	
	private LocalDateTime date =LocalDateTime.now();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Users getCustomer() {
		return customer;
	}

	public void setCustomer(Users customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Transaction(Long userId, Users customer, Order order, Seller seller, LocalDateTime date) {
		super();
		this.userId = userId;
		this.customer = customer;
		this.order = order;
		this.seller = seller;
		this.date = date;
	}

	public Transaction() {
		super();
	}

	@Override
	public String toString() {
		return "Transaction [userId=" + userId + ", customer=" + customer + ", order=" + order + ", seller=" + seller
				+ ", amount=" + amount + ", date=" + date + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, date, order, seller, userId);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Transaction(Long userId, Users customer, Order order, Seller seller, int amount, LocalDateTime date) {
		super();
		this.userId = userId;
		this.customer = customer;
		this.order = order;
		this.seller = seller;
		this.amount = amount;
		this.date = date;
	}

	
	
}
