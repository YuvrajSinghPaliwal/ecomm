package com.app.ecomm.entity;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String reviewText;
	
	private double rating;
	
	@ElementCollection
	private List<String> images=new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Product product;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Users user;
	
	
	private LocalDateTime createdAt=LocalDateTime.now();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getReviewText() {
		return reviewText;
	}


	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public Review(Long id, String reviewText, double rating, List<String> images, Product product, Users user,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.reviewText = reviewText;
		this.rating = rating;
		this.images = images;
		this.product = product;
		this.user = user;
		this.createdAt = createdAt;
	}


	public Review() {
		super();
	}


	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewText=" + reviewText + ", rating=" + rating + ", images=" + images
				+ ", product=" + product + ", user=" + user + ", createdAt=" + createdAt + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, images, product, rating, reviewText, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
				&& Objects.equals(images, other.images) && Objects.equals(product, other.product)
				&& Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating)
				&& Objects.equals(reviewText, other.reviewText) && Objects.equals(user, other.user);
	}
	
	
	
}
