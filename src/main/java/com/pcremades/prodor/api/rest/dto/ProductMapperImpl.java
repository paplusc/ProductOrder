package com.pcremades.prodor.api.rest.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pcremades.prodor.domain.Product;

@Component
public class ProductMapperImpl implements ProductMapper {
  @Override
  public Product toEntity(ProductDto productDto) {
    return new Product(productDto.getId(), productDto.getName(), productDto.getPrice());
  }

  @Override
  public Product toEntity(CreateProductRequest createProductRequest) {
    return new Product(null, createProductRequest.getName(), createProductRequest.getPrice());
  }

  @Override
  public List<Product> toEntity(List<ProductDto> productDtoList) {
    final List<Product> products = new ArrayList<>();
    for(ProductDto productDto : productDtoList) {
      products.add(toEntity(productDto));
    }
    return products;
  }

  @Override
  public ProductDto toDto(Product product) {
    return new ProductDto(product.getId(), product.getName(), product.getPrice());
  }

  @Override
  public List<ProductDto> toDto(List<Product> products) {
    final List<ProductDto> productDtoList = new ArrayList<>();
    for(Product product : products) {
      productDtoList.add(toDto(product));
    }
    return productDtoList;
  }
}
