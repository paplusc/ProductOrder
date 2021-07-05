package com.pcremades.prodor.usecases;

import static org.apache.logging.log4j.util.Strings.isBlank;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcremades.prodor.domain.Product;
import com.pcremades.prodor.infrastructure.ProductDocument;
import com.pcremades.prodor.infrastructure.ProductDocumentMapper;
import com.pcremades.prodor.infrastructure.ProductRepository;

@Service
public class ProductUseCase {

  private ProductRepository productRepository;
  private ProductDocumentMapper mapper;

  @Autowired
  public ProductUseCase(ProductRepository productRepository, ProductDocumentMapper mapper) {
    this.productRepository = productRepository;
    this.mapper = mapper;
  }

  public Product createProduct(Product product) {
    ProductDocument productDocument = mapper.toDocument(product);
    return mapper.toEntity(productRepository.save(productDocument));
  }

  public List<Product> getAllProducts() {
    return mapper.toEntity(productRepository.findAll());
  }

  public Product updateProduct(Product product) {
    Optional<ProductDocument> productDocumentOptional = productRepository.findById(product.getId());
    if (productDocumentOptional.isPresent()) {
      ProductDocument productDocument = productDocumentOptional.get();
      if (!isBlank(product.getName())) {
        productDocument.setName(product.getName());
      }
      if (product.getPrice() != null) {
        productDocument.setPrice(product.getPrice());
      }

      return mapper.toEntity(productRepository.save(productDocument));
    }

    return null;

  }
}
