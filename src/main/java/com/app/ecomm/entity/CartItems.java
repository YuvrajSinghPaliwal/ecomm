package com.app.ecomm.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItems {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JsonIgnore
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private int quantity;
	
	private int mrpPrice;
	
	private int sellingPrice;
	
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMrpPrice() {
		return mrpPrice;
	}

	public void setMrpPrice(int mrpPrice) {
		this.mrpPrice = mrpPrice;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public CartItems(Long id, Cart cart, Product product, int quantity, int mrpPrice, int sellingPrice, Long userId) {
		super();
		this.id = id;
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.mrpPrice = mrpPrice;
		this.sellingPrice = sellingPrice;
		this.userId = userId;
	}

	public CartItems() {
		super();
	}

	@Override
	public String toString() {
		return "CartItems [id=" + id + ", cart=" + cart + ", product=" + product + ", quantity=" + quantity
				+ ", mrpPrice=" + mrpPrice + ", sellingPrice=" + sellingPrice + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash( id, mrpPrice, product, quantity, sellingPrice, userId);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    CartItems that = (CartItems) obj;
	    return quantity == that.quantity &&
	           mrpPrice == that.mrpPrice &&
	           sellingPrice == that.sellingPrice &&
	           Objects.equals(id, that.id) &&
	           Objects.equals(product, that.product) &&
	           Objects.equals(userId, that.userId);
	    // ✅ EXCLUDE cart
	}
	
	
}
