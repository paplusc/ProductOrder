package com.pcremades.prodor.infrastructure;

import java.util.List;

import com.pcremades.prodor.domain.Product;

public interface ProductDocumentMapper {
  Product toEntity(ProductDocument productDocument);
  List<Product> toEntity(List<ProductDocument> productDocuments);
  ProductDocument toDocument(Product product);
}
