package com.pcremades.prodor.api.rest.dto;

import java.util.List;

import com.pcremades.prodor.domain.Product;

public interface ProductMapper {
  Product toEntity(ProductDto productDto);
  Product toEntity(CreateProductRequest createProductRequest);
  List<Product> toEntity(List<ProductDto> productDtoList);
  ProductDto toDto(Product product);
  List<ProductDto> toDto(List<Product> products);
}
