package com.hrmelo.cloudapps.controller.dto;

import java.util.List;

import com.hrmelo.cloudapps.domain.Product;

public class ShoppingCartRequestDto {

	private String userName;

	public ShoppingCartRequestDto(String userName, List<Product> products) {
		this.userName = userName;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
