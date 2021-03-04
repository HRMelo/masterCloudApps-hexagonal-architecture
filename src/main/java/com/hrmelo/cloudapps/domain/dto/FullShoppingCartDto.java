package com.hrmelo.cloudapps.domain.dto;

import java.util.List;

public class FullShoppingCartDto {

	private Long id;

	private boolean status;
	
	private List<ShoppingCartItemDto> items;
	
	private double totalPrice;
	
	public FullShoppingCartDto() {
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

	public List<ShoppingCartItemDto> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItemDto> items) {
		this.items = items;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
