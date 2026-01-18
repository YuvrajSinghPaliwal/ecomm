package com.app.ecomm.entity;

import java.util.Objects;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;;

@Entity
public class Deal {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int discount;
	
	@OneToOne
	private HomeCategory category;

	public Deal(Long id, int discount, HomeCategory category) {
		super();
		this.id = id;
		this.discount = discount;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public HomeCategory getCategory() {
		return category;
	}

	public void setCategory(HomeCategory category) {
		this.category = category;
	}

	public Deal() {
		super();
	}

	@Override
	public String toString() {
		return "Deal [id=" + id + ", discount=" + discount + ", category=" + category + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, discount, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deal other = (Deal) obj;
		return Objects.equals(category, other.category) && discount == other.discount && Objects.equals(id, other.id);
	}
	
	
}
