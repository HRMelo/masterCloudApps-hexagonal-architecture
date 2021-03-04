package com.hrmelo.cloudapps.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.hrmelo.cloudapps.domain.dto.FullShoppingCartDto;

public interface ShoppingCartService {

	FullShoppingCartDto createShoppingCart();
	
	FullShoppingCartDto getShoppingCart(Long id);
	
	FullShoppingCartDto finishShoppingCart(Long id);
	
	void deleteShoppingCart(Long id);
	
	FullShoppingCartDto addProduct(Long cartId, Long productId, int quantity) throws NotFoundException;
	
	FullShoppingCartDto deleteProduct(Long cartId, Long productId);
}
