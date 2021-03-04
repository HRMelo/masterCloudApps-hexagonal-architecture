package com.hrmelo.cloudapps.domain.repository;

import com.hrmelo.cloudapps.domain.dto.FullShoppingCartDto;

public interface ShoppingCartRepository {
	
	FullShoppingCartDto saveShoppingCart(FullShoppingCartDto fullShoppingCartDto);
	
	FullShoppingCartDto findShoppingCart(Long id);
	
	void deleteShoppingCart(Long id);
}
