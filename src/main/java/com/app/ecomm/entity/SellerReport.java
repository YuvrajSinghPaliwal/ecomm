package com.app.ecomm.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class SellerReport {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_user_id")
	private Seller seller;
	
	private int totalEarning;
	
	private int totalSales;
	
	private int totalRefunds;
	
	private int totalTax ;
	
	private int netEarnings;
	
	private int totalOrders=0;
	
	private int canceledOrders=0;
	
	private int totalTransactions=0;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public int getTotalEarning() {
		return totalEarning;
	}

	public void setTotalEarning(int totalEarning) {
		this.totalEarning = totalEarning;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}

	public int getTotalRefunds() {
		return totalRefunds;
	}

	public void setTotalRefunds(int totalRefunds) {
		this.totalRefunds = totalRefunds;
	}

	public int getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(int totalTax) {
		this.totalTax = totalTax;
	}

	public int getNetEarnings() {
		return netEarnings;
	}

	public void setNetEarnings(int netEarnings) {
		this.netEarnings = netEarnings;
	}

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public int getCanceledOrders() {
		return canceledOrders;
	}

	public void setCanceledOrders(int canceledOrders) {
		this.canceledOrders = canceledOrders;
	}

	public int getTotalTransactions() {
		return totalTransactions;
	}

	public void setTotalTransactions(int totalTransactions) {
		this.totalTransactions = totalTransactions;
	}

	public SellerReport(Long userId, Seller seller, int totalEarning, int totalSales, int totalRefunds, int totalTax,
			int netEarnings, int totalOrders, int canceledOrders, int totalTransactions) {
		super();
		this.userId = userId;
		this.seller = seller;
		this.totalEarning = totalEarning;
		this.totalSales = totalSales;
		this.totalRefunds = totalRefunds;
		this.totalTax = totalTax;
		this.netEarnings = netEarnings;
		this.totalOrders = totalOrders;
		this.canceledOrders = canceledOrders;
		this.totalTransactions = totalTransactions;
	}

	public SellerReport() {
		super();
	}

	@Override
	public String toString() {
		return "SellerReport [userId=" + userId + ", seller=" + seller + ", totalEarning=" + totalEarning
				+ ", totalSales=" + totalSales + ", totalRefunds=" + totalRefunds + ", totalTax=" + totalTax
				+ ", netEarnings=" + netEarnings + ", totalOrders=" + totalOrders + ", canceledOrders=" + canceledOrders
				+ ", totalTransactions=" + totalTransactions + "]";
	}

	
	
	
}
