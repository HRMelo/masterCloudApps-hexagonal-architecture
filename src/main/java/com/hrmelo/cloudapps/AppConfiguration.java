package com.hrmelo.cloudapps;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hrmelo.cloudapps.domain.repository.CheckAvailability;
import com.hrmelo.cloudapps.domain.repository.ProductRepository;
import com.hrmelo.cloudapps.domain.repository.ShoppingCartRepository;
import com.hrmelo.cloudapps.domain.service.ProductUseCase;
import com.hrmelo.cloudapps.domain.service.ProductUseCaseImpl;
import com.hrmelo.cloudapps.domain.service.ShoppingCartUseCase;
import com.hrmelo.cloudapps.domain.service.ShoppingCartUseCaseImpl;

@Configuration
public class AppConfiguration {

	@Bean
	public ProductUseCase productUseCase(ProductRepository productRepository) {
		return new ProductUseCaseImpl(productRepository);
	}
	
	@Bean
	public ShoppingCartUseCase shoppingCartUseCase(ShoppingCartRepository shoppingCartRepository, 
			ProductRepository productRepository,
			CheckAvailability checkAvailability,
			ModelMapper mapper) {
		return new ShoppingCartUseCaseImpl(
				shoppingCartRepository, 
				productRepository,
				checkAvailability,
				mapper);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
