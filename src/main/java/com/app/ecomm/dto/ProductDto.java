package com.app.ecomm.dto;

import java.util.List;
import java.util.Objects;

public class ProductDto {

	private String title;
	private String description;
	private int mrpPrice;
	private int sellingPrice;
	private String color;
	private List<String> images;
	private String category;
	private String category2;
	private String category3;
	private String sizes;
	public ProductDto() {
		super();
	}
	public ProductDto(String title, String description, int mrpPrice, int sellingPrice, String color,
			List<String> images, String category, String category2, String category3, String sizes) {
		super();
		this.title = title;
		this.description = description;
		this.mrpPrice = mrpPrice;
		this.sellingPrice = sellingPrice;
		this.color = color;
		this.images = images;
		this.category = category;
		this.category2 = category2;
		this.category3 = category3;
		this.sizes = sizes;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory2() {
		return category2;
	}
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	public String getCategory3() {
		return category3;
	}
	public void setCategory3(String category3) {
		this.category3 = category3;
	}
	public String getSizes() {
		return sizes;
	}
	public void setSizes(String sizes) {
		this.sizes = sizes;
	}
	@Override
	public String toString() {
		return "ProductDto [title=" + title + ", description=" + description + ", mrpPrice=" + mrpPrice
				+ ", sellingPrice=" + sellingPrice + ", color=" + color + ", images=" + images + ", category="
				+ category + ", category2=" + category2 + ", category3=" + category3 + ", sizes=" + sizes + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(category, category2, category3, color, description, images, mrpPrice, sellingPrice, sizes,
				title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDto other = (ProductDto) obj;
		return Objects.equals(category, other.category) && Objects.equals(category2, other.category2)
				&& Objects.equals(category3, other.category3) && Objects.equals(color, other.color)
				&& Objects.equals(description, other.description) && Objects.equals(images, other.images)
				&& mrpPrice == other.mrpPrice && sellingPrice == other.sellingPrice
				&& Objects.equals(sizes, other.sizes) && Objects.equals(title, other.title);
	}
	
	
	
}
