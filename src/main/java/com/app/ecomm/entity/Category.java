package com.app.ecomm.entity;

import java.util.Objects;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category(Long userId, String name) {
		super();
		this.id = userId;
		this.name = name;
	}

	public Category() {
		super();
	}

	@Override
	public String toString() {
		return "Category [userId=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(name, other.name) && Objects.equals(id, other.id);
	}
	
	
}
