package com.pcremades.prodor.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pcremades.prodor.api.rest.dto.CreateProductRequest;
import com.pcremades.prodor.api.rest.dto.ProductDto;
import com.pcremades.prodor.api.rest.dto.ProductMapper;
import com.pcremades.prodor.domain.Product;
import com.pcremades.prodor.usecases.ProductUseCase;

@RestController
public class ProductController {

  private ProductMapper mapper;
  private ProductUseCase productUseCase;

  @Autowired
  public ProductController(ProductMapper mapper, ProductUseCase productUseCase) {
    this.mapper = mapper;
    this.productUseCase = productUseCase;
  }


  @PostMapping(value = "/createProduct")
  public ResponseEntity<ProductDto> createProduct(@RequestBody final CreateProductRequest request) {
    final Product product = productUseCase.createProduct(mapper.toEntity(request));
    final ProductDto response = mapper.toDto(product);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/allProducts")
  public ResponseEntity<List<ProductDto>> allProducts() {
    final List<ProductDto> response = mapper.toDto(productUseCase.getAllProducts());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(value = "/updateProduct")
  public ResponseEntity<ProductDto> updateProduct(@RequestBody final ProductDto request) {
    final Product product = productUseCase.updateProduct(mapper.toEntity(request));
    final ProductDto response = mapper.toDto(product);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
