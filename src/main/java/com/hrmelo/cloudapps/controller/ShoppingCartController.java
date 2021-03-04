package com.hrmelo.cloudapps.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrmelo.cloudapps.controller.dto.ShoppingCartResponseDto;
import com.hrmelo.cloudapps.domain.dto.FullShoppingCartDto;
import com.hrmelo.cloudapps.service.ShoppingCartService;

@RestController
@RequestMapping("/api/shoppingcarts")
public class ShoppingCartController {
	
	private ShoppingCartService shoppingCartService;
	
	private ModelMapper mapper;
	
	public ShoppingCartController(ShoppingCartService shoppingCartService, ModelMapper mapper) {
		this.shoppingCartService = shoppingCartService;
		this.mapper = mapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ShoppingCartResponseDto> getShoppingCart(@PathVariable Long id) {
		return ResponseEntity.ok(
				this.convertFromFullShoppingCartDtoToShoppingCartResponse(
						this.shoppingCartService.getShoppingCart(id)));
	}

	@PostMapping
	public ResponseEntity<ShoppingCartResponseDto> createShoppingCart() {
		
		FullShoppingCartDto fullShoppingCartDto = this.shoppingCartService.createShoppingCart();
		
		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(fullShoppingCartDto.getId()).toUri();
		
		return ResponseEntity.created(location)
				.body(this.convertFromFullShoppingCartDtoToShoppingCartResponse(fullShoppingCartDto));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ShoppingCartResponseDto> finishShoppingCart(@PathVariable Long id) {
		
		FullShoppingCartDto finishedShoppingCart = this.shoppingCartService.finishShoppingCart(id);
		return ResponseEntity.ok(
				this.convertFromFullShoppingCartDtoToShoppingCartResponse(finishedShoppingCart));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ShoppingCartResponseDto> deleteShoppingCart(@PathVariable Long id) {
			
		this.shoppingCartService.deleteShoppingCart(id);
		return ResponseEntity.noContent().header("Content-Length", "0").build();
		
	}
	
	@PostMapping("/{cartId}/product/{productId}/quantity/{quantity}")
	public ResponseEntity<ShoppingCartResponseDto> addProduct(@PathVariable Long cartId,
															  @PathVariable Long productId,
															  @PathVariable int quantity) throws NotFoundException {
		
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(cartId).toUri();
		return ResponseEntity.created(location)
				.body(this.convertFromFullShoppingCartDtoToShoppingCartResponse(
						this.shoppingCartService.addProduct(cartId, productId, quantity)));
	}
	
	@DeleteMapping("/{cartId}/product/{productId}")
	public ResponseEntity<ShoppingCartResponseDto> deleteProduct(@PathVariable Long cartId,
															     @PathVariable Long productId) {
		
		return ResponseEntity.ok(this.convertFromFullShoppingCartDtoToShoppingCartResponse(
				this.shoppingCartService.deleteProduct(cartId, productId)));
	}
	
	private ShoppingCartResponseDto convertFromFullShoppingCartDtoToShoppingCartResponse(
			FullShoppingCartDto fullShoppingCartDto) {
		return mapper.map(fullShoppingCartDto, ShoppingCartResponseDto.class);
	}
}
