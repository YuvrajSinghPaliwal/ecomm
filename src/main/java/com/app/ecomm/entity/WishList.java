package com.app.ecomm.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class WishList {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Users user;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Product> products = new HashSet<>();

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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public WishList(Long id, Users user, Set<Product> products) {
		super();
		this.id = id;
		this.user = user;
		this.products = products;
	}

	public WishList() {
		super();
	}

	@Override
	public String toString() {
		return "WishList [id=" + id + ", user=" + user + ", products=" + products + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, products, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishList other = (WishList) obj;
		return Objects.equals(id, other.id) && Objects.equals(products, other.products)
				&& Objects.equals(user, other.user);
	}
	
	
}
