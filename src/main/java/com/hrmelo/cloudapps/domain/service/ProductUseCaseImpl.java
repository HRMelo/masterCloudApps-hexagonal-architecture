package com.hrmelo.cloudapps.domain.service;

import java.util.List;

import com.hrmelo.cloudapps.domain.dto.FullProductDto;
import com.hrmelo.cloudapps.domain.repository.ProductRepository;

public class ProductUseCaseImpl implements ProductUseCase {
	
	private ProductRepository productRepository;
	
	public ProductUseCaseImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<FullProductDto> findAllProducts() {
		return this.productRepository.findAllProducts();
	}

	@Override
	public FullProductDto findProductById(Long id) {
		return this.productRepository.findProductById(id);
	}

	@Override
	public FullProductDto createProduct(FullProductDto fullProductDto) {
		return this.productRepository.createProduct(fullProductDto);
	}

	@Override
	public FullProductDto updateProduct(FullProductDto fullProductDto) {
		this.findProductById(fullProductDto.getId());
		return this.productRepository.updateProduct(fullProductDto);
	}

	@Override
	public void deleteProduct(Long id) {
		this.findProductById(id);
		this.productRepository.deleteProduct(id);
	}

}
