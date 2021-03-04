package com.hrmelo.cloudapps.infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hrmelo.cloudapps.domain.dto.FullShoppingCartDto;
import com.hrmelo.cloudapps.domain.repository.ShoppingCartRepository;
import com.hrmelo.cloudapps.infrastructure.model.ShoppingCartEntity;
import com.hrmelo.cloudapps.infrastructure.repository.ShoppingCartJPARepository;

@Component
public class ShoppingCartRepositoryAdapter implements ShoppingCartRepository {
	
	private ShoppingCartJPARepository shoppingCartRepository;
	
	private ModelMapper mapper;

	public ShoppingCartRepositoryAdapter(ShoppingCartJPARepository shoppingCartRepository,
			ModelMapper mapper) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.mapper = mapper;
	}

	public FullShoppingCartDto saveShoppingCart(FullShoppingCartDto fullShoppingCartDto) {
		ShoppingCartEntity shoppingCartEntity = this.convertFromFullShoppingCartDtoToEntity(fullShoppingCartDto);
		return convertFromEntityToFullShoppingCart(shoppingCartRepository.save(shoppingCartEntity));
	}
	
	@Override
	public FullShoppingCartDto findShoppingCart(Long id) {
		return this.convertFromEntityToFullShoppingCart(
				this.shoppingCartRepository.findById(id).orElseThrow(ShoppingCartNotFoundException::new));
	}

	@Override
	public void deleteShoppingCart(Long id) {
		this.shoppingCartRepository.deleteById(id);
	}
	
	private FullShoppingCartDto convertFromEntityToFullShoppingCart(ShoppingCartEntity entity) {
		return this.mapper.map(entity, FullShoppingCartDto.class);
	}
	
	private ShoppingCartEntity convertFromFullShoppingCartDtoToEntity(FullShoppingCartDto fullShoppingCartDto) {
		return this.mapper.map(fullShoppingCartDto, ShoppingCartEntity.class);
	}
}
