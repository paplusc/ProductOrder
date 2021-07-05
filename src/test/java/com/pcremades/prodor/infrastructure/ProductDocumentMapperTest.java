package com.pcremades.prodor.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.pcremades.prodor.TestUtils;
import com.pcremades.prodor.api.rest.dto.ProductDto;
import com.pcremades.prodor.domain.Product;

public class ProductDocumentMapperTest {

  private ProductDocumentMapper mapper = new ProductDocumentMapperImpl();

  @Nested
  class ToEntity {
    @Test
    void whenProductDocument() {
      final ProductDocument document = new ProductDocument();
      document.setId(1L);
      document.setName("name");
      document.setPrice(10.10);

      final Product product = mapper.toEntity(document);

      assertThat(product.getId()).isEqualTo(1L);
      assertThat(product.getName()).isEqualTo("name");
      assertThat(product.getPrice()).isEqualTo(10.10);
    }
    @Test
    void whenProductDocumentList() {
      final List<ProductDocument> productDocuments = TestUtils.buildListOfDocumentProducts();

      final List<Product> response = mapper.toEntity(productDocuments);

      assertThat(response).extracting("id", "name", "price")
                 .contains(
                            tuple(1L, "name1", 10.00),
                            tuple(2L, "name2", 20.00),
                            tuple(3L, "name3", 30.00)
                 );
    }
  }
  @Nested
  class ToDocument {
    @Test
    void whenProduct() {
      final Product product = new Product(1L,"name", 10.10);

      final ProductDocument response = mapper.toDocument(product);

      assertThat(response.getId()).isEqualTo(1L);
      assertThat(response.getName()).isEqualTo("name");
      assertThat(response.getPrice()).isEqualTo(10.10);
    }
  }
}
