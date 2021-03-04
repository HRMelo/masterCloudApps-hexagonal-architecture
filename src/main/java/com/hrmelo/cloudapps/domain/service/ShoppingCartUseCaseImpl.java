package com.hrmelo.cloudapps.domain.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.hrmelo.cloudapps.domain.Product;
import com.hrmelo.cloudapps.domain.ShoppingCart;
import com.hrmelo.cloudapps.domain.ShoppingCartItem;
import com.hrmelo.cloudapps.domain.dto.FullProductDto;
import com.hrmelo.cloudapps.domain.dto.FullShoppingCartDto;
import com.hrmelo.cloudapps.domain.repository.CheckAvailability;
import com.hrmelo.cloudapps.domain.repository.ProductRepository;
import com.hrmelo.cloudapps.domain.repository.ShoppingCartRepository;

public class ShoppingCartUseCaseImpl implements ShoppingCartUseCase {
	
	private ShoppingCartRepository shoppingCartRepository;
	
	private ProductRepository productRepository;
	
	private CheckAvailability checkAvailability;
	
	private ModelMapper mapper;
	
	public ShoppingCartUseCaseImpl(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository,
			CheckAvailability checkAvailability, ModelMapper mapper) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.productRepository = productRepository;
		this.checkAvailability = checkAvailability;
		this.mapper = mapper;
	}

	@Override
	public FullShoppingCartDto findShoppingCart(Long id) {
		return this.shoppingCartRepository.findShoppingCart(id);
	}

	@Override
	public FullShoppingCartDto createShoppingCart() {
		ShoppingCart shoppingCart = new ShoppingCart();
		return shoppingCartRepository.saveShoppingCart(
				this.convertFromShoppingCartToFullShoppingCartDto(shoppingCart));
	}

	@Override
	public FullShoppingCartDto finishShoppingCart(Long id) {	
		FullShoppingCartDto fullShoppingCartDto = this.shoppingCartRepository.findShoppingCart(id);
		
		ShoppingCart shoppingCart = this.convertFromFullShoppingCartToShoppingCart(fullShoppingCartDto);
		shoppingCart.setCheckAvailability(checkAvailability);
		shoppingCart.validate();
		
		return shoppingCartRepository.saveShoppingCart(
				this.convertFromShoppingCartToFullShoppingCartDto(shoppingCart));
	}

	@Override
	public void deleteShoppingCart(Long id) {
		this.findShoppingCart(id);
		this.shoppingCartRepository.deleteShoppingCart(id);
	}

	@Override
	public FullShoppingCartDto updateShoppingCartWithProductAndQuantity(Long cartId, Long productId, int quantity)
			throws NotFoundException {
		FullShoppingCartDto fullShoppingCartDto = shoppingCartRepository.findShoppingCart(cartId);
		FullProductDto fullProductDto = this.productRepository.findProductById(productId);
		
		ShoppingCart shoppingCart = this.convertFromFullShoppingCartToShoppingCart(fullShoppingCartDto);
		shoppingCart.removeItem(productId);
		
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(
				this.mapper.map(fullProductDto, Product.class), 
				quantity);
		shoppingCart.addItem(shoppingCartItem);
		
		return shoppingCartRepository.saveShoppingCart(
				this.convertFromShoppingCartToFullShoppingCartDto(shoppingCart));
	}

	@Override
	public FullShoppingCartDto deleteProduct(Long shoppingCartId, Long productId) {
		FullShoppingCartDto fullShoppingCartDto = shoppingCartRepository.findShoppingCart(shoppingCartId);
		
		ShoppingCart shoppingCart = this.convertFromFullShoppingCartToShoppingCart(fullShoppingCartDto);
		shoppingCart.removeItem(productId);
		
		FullShoppingCartDto newFullShoppingCart = mapper.map(shoppingCart, FullShoppingCartDto.class);
		
		return shoppingCartRepository.saveShoppingCart(newFullShoppingCart);
	}
	
	private ShoppingCart convertFromFullShoppingCartToShoppingCart(FullShoppingCartDto fullShoppingCartDto) {
		return this.mapper.map(fullShoppingCartDto, ShoppingCart.class);
	}
	
	private FullShoppingCartDto convertFromShoppingCartToFullShoppingCartDto(ShoppingCart shoppingCart) {
		return this.mapper.map(shoppingCart, FullShoppingCartDto.class);
	}

}
