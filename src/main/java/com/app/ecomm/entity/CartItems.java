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
	
	private Integer quantity;
	
	private Integer mrpPrice;
	
	private Integer size;
	
	private Integer sellingPrice;
	
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getMrpPrice() {
		return mrpPrice;
	}

	public void setMrpPrice(Integer mrpPrice) {
		this.mrpPrice = mrpPrice;
	}

	public Integer getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public CartItems(Long id, Cart cart, Product product, Integer quantity, Integer mrpPrice, Integer sellingPrice,
			Long userId) {
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
		return Objects.hash(cart, id, mrpPrice, product, quantity, sellingPrice, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItems other = (CartItems) obj;
		return Objects.equals(cart, other.cart) && Objects.equals(id, other.id)
				&& Objects.equals(mrpPrice, other.mrpPrice) && Objects.equals(product, other.product)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(sellingPrice, other.sellingPrice)
				&& Objects.equals(userId, other.userId);
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	
	
}
