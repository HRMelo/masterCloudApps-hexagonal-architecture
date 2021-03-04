package com.hrmelo.cloudapps.domain.service;

import java.util.List;

import com.hrmelo.cloudapps.domain.dto.FullProductDto;

public interface ProductUseCase {

	List<FullProductDto> findAllProducts();
	
	FullProductDto findProductById(Long id);
	
	FullProductDto createProduct(FullProductDto fullProductDto);
	
	FullProductDto updateProduct(FullProductDto fullProductDto);
	
	void deleteProduct(Long id);
}
