package com.pcremades.prodor.api.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pcremades.prodor.api.rest.dto.CreateProductRequest;
import com.pcremades.prodor.api.rest.dto.ProductDto;
import com.pcremades.prodor.api.rest.dto.ProductMapper;
import com.pcremades.prodor.api.rest.dto.ProductMapperImpl;
import com.pcremades.prodor.domain.Product;
import com.pcremades.prodor.usecases.ProductUseCase;

public class ProductControllerTest {

  private ProductMapper mapper = mock(ProductMapperImpl.class);
  private ProductUseCase productUseCase = mock(ProductUseCase.class);
  private ProductController productController = new ProductController(mapper, productUseCase);

  @Nested
  class CreateProduct {
    @Test
    void ok() {
      CreateProductRequest request = new CreateProductRequest( "name", 10.10);
      when(mapper.toEntity(any(CreateProductRequest.class))).thenReturn(null);
      when(productUseCase.createProduct(any(Product.class))).thenReturn(null);
      when(mapper.toDto(any(Product.class))).thenReturn(new ProductDto(1L,"name", 10.10));

      ResponseEntity<ProductDto> response = productController.createProduct(request);

      assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
    }
  }

  @Nested
  class GetAllProducts {
    @Test
    void ok() {
      when(productUseCase.getAllProducts()).thenReturn(new ArrayList<>());
      when(mapper.toDto(anyList())).thenReturn(new ArrayList<>());

      ResponseEntity<List<ProductDto>> response = productController.allProducts();

      assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
    }
  }

  @Nested
  class UpdateProduct {
    @Test
    void ok() {
      ProductDto request = new ProductDto( 1L,"name", 10.10);
      when(mapper.toEntity(any(ProductDto.class))).thenReturn(null);
      when(productUseCase.createProduct(any())).thenReturn(null);
      when(mapper.toDto(any(Product.class))).thenReturn(new ProductDto(1L,"name", 10.10));

      ResponseEntity<ProductDto> response = productController.updateProduct(request);

      assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
    }
  }
}
