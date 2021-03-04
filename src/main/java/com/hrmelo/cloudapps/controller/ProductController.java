package com.hrmelo.cloudapps.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrmelo.cloudapps.controller.dto.ProductRequestDto;
import com.hrmelo.cloudapps.controller.dto.ProductResponseDto;
import com.hrmelo.cloudapps.domain.dto.FullProductDto;
import com.hrmelo.cloudapps.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private ProductService productService;
	
	private ModelMapper mapper;
	
	public ProductController(ProductService productService, ModelMapper mapper) {
		this.productService = productService;
		this.mapper = mapper;
	}

	@GetMapping
	public List<ProductResponseDto> getAllProducts() {
		return this.productService.findAll()
				.stream()
				.map(this::convertToProductResponseDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
		return ResponseEntity.ok(
				this.convertToProductResponseDto((this.productService.findProductById(id))));
	}
	
	@PostMapping
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
		ProductResponseDto productResponse = 
				this.convertToProductResponseDto(
						this.productService.createProduct(this.convertToFullProductDto(productRequestDto)));
		
		return ResponseEntity.created(fromCurrentRequest().path("/{id}").buildAndExpand(productResponse.getId())
				.toUri())
				.body(productResponse);
	}
	
	@PutMapping
	public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto productRequestDto) {
		ProductResponseDto productResponse = 
					this.convertToProductResponseDto(
							this.productService.updateProduct(this.convertToFullProductDto(productRequestDto)));
			
		return ResponseEntity.created(fromCurrentRequest().path("/{id}").buildAndExpand(productResponse.getId())
				.toUri())
				.body(productResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable Long id) {
			
		this.productService.deleteProduct(id);
		return ResponseEntity.noContent().header("Content-Length", "0").build();
	}
	
	private ProductResponseDto convertToProductResponseDto(FullProductDto fullProductDto) {
		return mapper.map(fullProductDto, ProductResponseDto.class);
	}
	
	private FullProductDto convertToFullProductDto(ProductRequestDto productRequestDto) {
		return mapper.map(productRequestDto, FullProductDto.class);
	}
}
