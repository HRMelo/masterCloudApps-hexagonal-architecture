package com.hrmelo.cloudapps.domain;

import java.util.ArrayList;
import java.util.List;

import com.hrmelo.cloudapps.domain.repository.CheckAvailability;

public class ShoppingCart {

	private Long id;

	private boolean status;
	
	private List<ShoppingCartItem> items;
	
	private CheckAvailability checkAvailability;

	public ShoppingCart() {
		super();
		this.status = false;
		this.items = new ArrayList<>();
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

	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}
	
	public CheckAvailability getCheckAvailability() {
		return checkAvailability;
	}

	public void setCheckAvailability(CheckAvailability checkAvailability) {
		this.checkAvailability = checkAvailability;
	}
	
	public void addItem(ShoppingCartItem shoppingCartItem) {
		this.items.add(shoppingCartItem);
	}
	
	public void removeItem(Long productId) {
		this.items.removeIf(item -> item.getProduct().getId().equals(productId));
	}

	public void validate() {
		if(!this.status) {
			if(this.checkAvailability.checkProductAvailability(this.items)) {
				throw new ShoppingCartNoStockException("Not enough stock");
			}
			this.status = true;
		}
	}
}
