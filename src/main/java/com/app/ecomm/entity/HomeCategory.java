package com.app.ecomm.entity;

import java.util.List;
import java.util.Objects;

import com.app.ecomm.enums.HomeCategorySection;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class HomeCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String image;
	
	private String CategoryId;
	
	private HomeCategorySection section;
	
	@ManyToMany
    private List<Product> products;

	public HomeCategory(Long id, String name, String image, String categoryId, HomeCategorySection section,
			List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		CategoryId = categoryId;
		this.section = section;
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(String categoryId) {
		CategoryId = categoryId;
	}

	public HomeCategorySection getSection() {
		return section;
	}

	public void setSection(HomeCategorySection section) {
		this.section = section;
	}

	public HomeCategory(Long id, String name, String image, String categoryId, HomeCategorySection section) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		CategoryId = categoryId;
		this.section = section;
	}

	public HomeCategory() {
		super();
	}

	@Override
	public String toString() {
		return "HomeCategory [id=" + id + ", name=" + name + ", image=" + image + ", CategoryId=" + CategoryId
				+ ", section=" + section + ", products=" + products + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(CategoryId, id, image, name, section);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HomeCategory other = (HomeCategory) obj;
		return Objects.equals(CategoryId, other.CategoryId) && Objects.equals(id, other.id)
				&& Objects.equals(image, other.image) && Objects.equals(name, other.name) && section == other.section;
	}

	public void setProducts(List<Product> products) {
		// TODO Auto-generated method stub
		
	}
	
	
}
