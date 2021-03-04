package com.hrmelo.cloudapps.service;

import java.util.List;

import com.hrmelo.cloudapps.domain.dto.FullProductDto;

public interface ProductService {

	List<FullProductDto> findAll();
	
	FullProductDto findProductById(Long id);
	
	FullProductDto createProduct(FullProductDto fullProductDto);
	
	FullProductDto updateProduct(FullProductDto fullProductDto);
	
	void deleteProduct(Long id);
}
