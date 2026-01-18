package com.app.ecomm.entity;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String description;
	
	private int sellingPrice;
	
	private int mrpPrice;
	
	private int discountPercentage;
	
	private int quantity;
	
	private String color;
	
	@ElementCollection
	private List<String> images=new ArrayList<>();
	
	private int numRating;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @JsonIgnore  // 🔥 ADD THIS
    private Seller seller;
    
    @OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
    @JsonIgnore  // 🔥 ADD THIS
    private List<Review> reviews = new ArrayList<>();
	
	@CreatedDate
    @Column(updatable = false)
	private LocalDate createdAt;
	
	private String sizes;
	

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getMrpPrice() {
		return mrpPrice;
	}

	public void setMrpPrice(int mrpPrice) {
		this.mrpPrice = mrpPrice;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public int getNumRating() {
		return numRating;
	}

	public void setNumRating(int numRating) {
		this.numRating = numRating;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", sellingPrice="
				+ sellingPrice + ", mrpPrice=" + mrpPrice + ", discountPercentage=" + discountPercentage + ", quantity="
				+ quantity + ", color=" + color + ", images=" + images + ", numRating=" + numRating + ", category="
				+ category + ", seller=" + seller + ", createdAt=" + createdAt + ", sizes=" + sizes + ", reviews="
				+ reviews + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, color, createdAt, description, discountPercentage, id, images, mrpPrice,
				numRating, quantity, reviews, seller, sellingPrice, sizes, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && Objects.equals(color, other.color)
				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(description, other.description)
				&& discountPercentage == other.discountPercentage && Objects.equals(id, other.id)
				&& Objects.equals(images, other.images) && mrpPrice == other.mrpPrice && numRating == other.numRating
				&& quantity == other.quantity && Objects.equals(reviews, other.reviews)
				&& Objects.equals(seller, other.seller) && sellingPrice == other.sellingPrice
				&& Objects.equals(sizes, other.sizes) && Objects.equals(title, other.title);
	}

	public Product(Long id, String title, String description, int sellingPrice, int mrpPrice, int discountPercentage,
			int quantity, String color, List<String> images, int numRating, Category category, Seller seller,
			LocalDate createdAt, String sizes, List<Review> reviews) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.sellingPrice = sellingPrice;
		this.mrpPrice = mrpPrice;
		this.discountPercentage = discountPercentage;
		this.quantity = quantity;
		this.color = color;
		this.images = images;
		this.numRating = numRating;
		this.category = category;
		this.seller = seller;
		this.createdAt = createdAt;
		this.sizes = sizes;
		this.reviews = reviews;
	}

	public Product() {
		super();
	}
	
	
}
