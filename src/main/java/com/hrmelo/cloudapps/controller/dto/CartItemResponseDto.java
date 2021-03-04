package com.hrmelo.cloudapps.controller.dto;

public class CartItemResponseDto {

	private ProductResponseDto product;

	private int quantity;
	
	public CartItemResponseDto() {
	}

	public CartItemResponseDto(ProductResponseDto product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public ProductResponseDto getProduct() {
		return product;
	}

	public void setProduct(ProductResponseDto product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
