package com.app.ecomm.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)  // 🔥 CHANGED!
    @JoinColumn(name = "user_user_id", unique = true)  // Unique FK!
    private Users user;

	
	@OneToMany(mappedBy="cart",cascade =CascadeType.ALL,orphanRemoval=true)
	private Set<CartItems> cartItems=new HashSet<>();
	
	private int items;
	
	private double totalSellingPrice;
	
	private double totalMrpPrice;
	
	private double discount;
	
	private String couponCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Set<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public double getTotalSellingPrice() {
		return totalSellingPrice;
	}

	public void setTotalSellingPrice(double totalSellingPrice) {
		this.totalSellingPrice = totalSellingPrice;
	}

	public double getTotalMrpPrice() {
		return totalMrpPrice;
	}

	public void setTotalMrpPrice(double totalMrpPrice) {
		this.totalMrpPrice = totalMrpPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Cart(Long id, Users user, Set<CartItems> cartItems, int items, double totalSellingPrice,
			double totalMrpPrice, double discount, String couponCode) {
		super();
		this.id = id;
		this.user = user;
		this.cartItems = cartItems;
		this.items = items;
		this.totalSellingPrice = totalSellingPrice;
		this.totalMrpPrice = totalMrpPrice;
		this.discount = discount;
		this.couponCode = couponCode;
	}

	public Cart() {
		super();
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", cartItems=" + cartItems + ", items=" + items
				+ ", totalSellingPrice=" + totalSellingPrice + ", totalMrpPrice=" + totalMrpPrice + ", discount="
				+ discount + ", couponCode=" + couponCode + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(couponCode, discount, id, items, totalMrpPrice, totalSellingPrice, user);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Cart cart = (Cart) obj;
	    return items == cart.items &&
	           Double.compare(cart.totalMrpPrice, totalMrpPrice) == 0 &&
	           Double.compare(cart.totalSellingPrice, totalSellingPrice) == 0 &&
	           Double.compare(cart.discount, discount) == 0 &&
	           Objects.equals(id, cart.id) &&
	           Objects.equals(user, cart.user) &&
	           Objects.equals(couponCode, cart.couponCode);
	    // ✅ EXCLUDE cartItems
	}
	
	
	
}

