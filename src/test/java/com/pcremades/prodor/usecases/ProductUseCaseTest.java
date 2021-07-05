package com.pcremades.prodor.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pcremades.prodor.TestUtils;
import com.pcremades.prodor.domain.Product;
import com.pcremades.prodor.infrastructure.ProductDocument;
import com.pcremades.prodor.infrastructure.ProductDocumentMapper;
import com.pcremades.prodor.infrastructure.ProductDocumentMapperImpl;
import com.pcremades.prodor.infrastructure.ProductRepository;

public class ProductUseCaseTest {

  private ProductRepository productRepository = mock(ProductRepository.class);
  private ProductDocumentMapper mapper = mock(ProductDocumentMapperImpl.class);
  private ProductUseCase productUseCase = new ProductUseCase(productRepository, mapper);

  @Test
  void createProduct() {
    final Product product = new Product(null, "name", 10.10);
    when(mapper.toDocument(any())).thenReturn(new ProductDocument());
    when(productRepository.save(any())).thenReturn(new ProductDocument());
    when(mapper.toEntity(any(ProductDocument.class))).thenReturn(new Product(1L, "name", 10.10));

    final Product response = productUseCase.createProduct(product);

    assertThat(response.getId()).isEqualTo(1L);
    assertThat(response.getName()).isEqualTo("name");
    assertThat(response.getPrice()).isEqualTo(10.10);
  }

  @Test
  void getAllProducts() {
    when(productRepository.findAll()).thenReturn(new ArrayList<>());
    when(mapper.toEntity(anyList())).thenReturn(TestUtils.buildListOfProducts());

    final List<Product> response = productUseCase.getAllProducts();

    assertThat(response).extracting("id", "name", "price")
               .contains(
                          tuple(1L, "name1", 10.00),
                          tuple(2L, "name2", 20.00),
                          tuple(3L, "name3", 30.00)
               );
  }

  @Test
  void updateProduct() {
    final Product product = new Product(1L ,"name", 10.10);
    when(productRepository.findById(any())).thenReturn(Optional.of(new ProductDocument()));
    when(productRepository.save(any())).thenReturn(new ProductDocument());
    when(mapper.toEntity(any(ProductDocument.class))).thenReturn(new Product(1L, "name", 10.10));

    final Product response = productUseCase.updateProduct(product);

    assertThat(response.getId()).isEqualTo(1L);
    assertThat(response.getName()).isEqualTo("name");
    assertThat(response.getPrice()).isEqualTo(10.10);
  }
}
