package com.hrmelo.cloudapps.service.impl;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.hrmelo.cloudapps.domain.dto.FullShoppingCartDto;
import com.hrmelo.cloudapps.domain.service.ShoppingCartUseCase;
import com.hrmelo.cloudapps.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	private ShoppingCartUseCase shoppingUseCase;
	
	public ShoppingCartServiceImpl(ShoppingCartUseCase shoppingUseCase) {
		this.shoppingUseCase = shoppingUseCase;
	}

	@Override
	public FullShoppingCartDto createShoppingCart() {
		return this.shoppingUseCase.createShoppingCart();
	}

	@Override
	public FullShoppingCartDto getShoppingCart(Long id) {
		return this.shoppingUseCase.findShoppingCart(id);
	}
	
	@Override
	public FullShoppingCartDto finishShoppingCart(Long id) {
		return this.shoppingUseCase.finishShoppingCart(id);
	}

	@Override
	public void deleteShoppingCart(Long id) {
		this.getShoppingCart(id);
		this.shoppingUseCase.deleteShoppingCart(id);
	}

	@Override
	public FullShoppingCartDto addProduct(Long cartId, Long productId, int quantity) throws NotFoundException {
		return this.shoppingUseCase.updateShoppingCartWithProductAndQuantity(cartId, productId, quantity);
	}

	@Override
	public FullShoppingCartDto deleteProduct(Long cartId, Long productId) {
		return this.shoppingUseCase.deleteProduct(cartId, productId);
	}
}
