package com.hrmelo.cloudapps.domain.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.hrmelo.cloudapps.domain.dto.FullShoppingCartDto;

public interface ShoppingCartUseCase {

	FullShoppingCartDto createShoppingCart();
	
	FullShoppingCartDto finishShoppingCart(Long id);
	
	FullShoppingCartDto findShoppingCart(Long id);
	
	void deleteShoppingCart(Long id);
	
	FullShoppingCartDto updateShoppingCartWithProductAndQuantity(Long cartId, Long productId, int quantity)
			throws NotFoundException;
	
	FullShoppingCartDto deleteProduct(Long shoppingCartId, Long productId);
}
