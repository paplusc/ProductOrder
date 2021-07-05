package com.pcremades.prodor.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pcremades.prodor.domain.Product;

@Component
public class ProductDocumentMapperImpl implements ProductDocumentMapper {

  @Override
  public Product toEntity(ProductDocument productDocument) {
    return new Product(productDocument.getId(), productDocument.getName(), productDocument.getPrice());
  }

  @Override
  public List<Product> toEntity(List<ProductDocument> productDocuments) {
    final List<Product> products = new ArrayList<>();
    for(ProductDocument productDocument : productDocuments) {
      products.add(toEntity(productDocument));
    }
    return products;
  }

  @Override
  public ProductDocument toDocument(Product product) {
    ProductDocument productDocument = new ProductDocument();
    productDocument.setId(product.getId());
    productDocument.setName(product.getName());
    productDocument.setPrice(product.getPrice());
    return productDocument;
  }
}
