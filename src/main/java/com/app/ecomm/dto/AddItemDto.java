package com.app.ecomm.dto;

public class AddItemDto {

	private String size;
	
	private int quantity;
	
	private Long productid;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public AddItemDto(String size, int quantity, Long productid) {
		super();
		this.size = size;
		this.quantity = quantity;
		this.productid = productid;
	}

	public AddItemDto() {
		super();
	}
	
	
	
	
}
