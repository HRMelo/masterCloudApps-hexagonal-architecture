package com.hrmelo.cloudapps.infrastructure.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPPING_CARTS")
public class ShoppingCartEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean status;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ShoppingCartItemEntity> items;
	
	public ShoppingCartEntity() {
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

	public List<ShoppingCartItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItemEntity> items) {
		this.items = items;
	}

	public double getTotalPrice() {
		double totaPrice = 0;
		
		if(this.items != null) {
			for(ShoppingCartItemEntity shoppingCartEntity : this.items) {
				totaPrice += shoppingCartEntity.getPrice();
			}
		}
		return totaPrice;
	}
}
