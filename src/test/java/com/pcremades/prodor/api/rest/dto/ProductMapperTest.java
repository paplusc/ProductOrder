package com.pcremades.prodor.api.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.pcremades.prodor.TestUtils;
import com.pcremades.prodor.domain.Product;

public class ProductMapperTest {

  private ProductMapper mapper = new ProductMapperImpl();

  @Nested
  class ToEntity {
    @Test
    void whenProductDto() {
      ProductDto productDto = new ProductDto(1L, "name", 10.10);

      Product product = mapper.toEntity(productDto);

      assertThat(product.getId()).isEqualTo(1L);
      assertThat(product.getName()).isEqualTo("name");
      assertThat(product.getPrice()).isEqualTo(10.10);
    }
    @Test
    void whenCreateProductRequest() {
      CreateProductRequest createProductRequest = new CreateProductRequest( "name", 10.10);

      Product product = mapper.toEntity(createProductRequest);

      assertThat(product.getId()).isNull();
      assertThat(product.getName()).isEqualTo("name");
      assertThat(product.getPrice()).isEqualTo(10.10);
    }
    @Test
    void whenListProductDto() {
      List<ProductDto> productDtoList = TestUtils.buildListOfProductsDto();

      List<Product> response = mapper.toEntity(productDtoList);

      assertThat(response).extracting("id", "name", "price")
                 .contains(
                            tuple(1L, "name1", 10.00),
                            tuple(2L, "name2", 20.00),
                            tuple(3L, "name3", 30.00)
                 );
    }
  }

  @Nested
  class ToDto {
    @Test
    void whenProduct() {
      final Product product = new Product(1L,"name", 10.10);

      final ProductDto productDto = mapper.toDto(product);

      assertThat(productDto.getId()).isEqualTo(1L);
      assertThat(productDto.getName()).isEqualTo("name");
      assertThat(productDto.getPrice()).isEqualTo(10.10);
    }
    @Test
    void whenListProduct() {
      List<Product> products = TestUtils.buildListOfProducts();

      List<ProductDto> response = mapper.toDto(products);

      assertThat(response).extracting("id", "name", "price")
                 .contains(
                            tuple(1L, "name1", 10.00),
                            tuple(2L, "name2", 20.00),
                            tuple(3L, "name3", 30.00)
                 );
    }
  }
}
