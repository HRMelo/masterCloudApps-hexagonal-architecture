package com.hrmelo.cloudapps.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hrmelo.cloudapps.domain.dto.FullProductDto;
import com.hrmelo.cloudapps.domain.service.ProductUseCase;
import com.hrmelo.cloudapps.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductUseCase productUseCase;
	
	public ProductServiceImpl(ProductUseCase productUseCase) {
		this.productUseCase = productUseCase;
	}

	@Override
	public List<FullProductDto> findAll() {
		return this.productUseCase.findAllProducts();
	}
	
	@Override
	public FullProductDto findProductById(Long id) {
		return this.productUseCase.findProductById(id);
	}

	@Override
	public FullProductDto createProduct(FullProductDto fullProductDto) {
		return this.productUseCase.createProduct(fullProductDto);
	}

	@Override
	public FullProductDto updateProduct(FullProductDto fullProductDto) {
		return this.productUseCase.updateProduct(fullProductDto);
	}

	@Override
	public void deleteProduct(Long id) {
		this.productUseCase.deleteProduct(id);
	}

}
