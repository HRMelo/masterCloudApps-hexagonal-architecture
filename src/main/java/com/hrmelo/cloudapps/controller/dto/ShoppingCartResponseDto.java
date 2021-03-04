package com.hrmelo.cloudapps.controller.dto;

import java.util.List;

public class ShoppingCartResponseDto {

	private Long id;

	private boolean status;

	private List<CartItemResponseDto> items;
	
	private double totalPrice;
	
	public ShoppingCartResponseDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<CartItemResponseDto> getItems() {
		return items;
	}

	public void setItems(List<CartItemResponseDto> items) {
		this.items = items;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
