package com.hrmelo.cloudapps.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hrmelo.cloudapps.domain.dto.FullProductDto;
import com.hrmelo.cloudapps.domain.repository.ProductRepository;
import com.hrmelo.cloudapps.infrastructure.model.ProductEntity;
import com.hrmelo.cloudapps.infrastructure.repository.ProductJPARepository;

@Component
public class ProductRepositoryAdapter implements ProductRepository {
	
	private ProductJPARepository productRepository;
	
	private ModelMapper mapper;
	
	public ProductRepositoryAdapter(ProductJPARepository productRepository,
			ModelMapper mapper) {
		this.productRepository = productRepository;
		this.mapper = mapper;
	}

	@Override
	public List<FullProductDto> findAllProducts() {
		return this.productRepository.findAll()
				.stream()
				.map(this::convertFromEntityToFullProductDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public FullProductDto findProductById(Long id) {
		ProductEntity product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
		return this.convertFromEntityToFullProductDto(product);
	}

	@Override
	public FullProductDto createProduct(FullProductDto fullProductDto) {
		return convertFromEntityToFullProductDto(
				this.productRepository.save(this.convertFromFullProductDtoToEntity(fullProductDto)));
	}
	
	@Override
	public FullProductDto updateProduct(FullProductDto fullProductDto) {
		return convertFromEntityToFullProductDto(
				this.productRepository.save(this.convertFromFullProductDtoToEntity(fullProductDto)));
	}
	
	@Override
	public void deleteProduct(Long id) {
		this.productRepository.deleteById(id);
	}

	private ProductEntity convertFromFullProductDtoToEntity(FullProductDto fullProductDto) {
		return mapper.map(fullProductDto, ProductEntity.class);
	}
	
	private FullProductDto convertFromEntityToFullProductDto(ProductEntity productEntity) {
		return mapper.map(productEntity, FullProductDto.class);
	}
}
