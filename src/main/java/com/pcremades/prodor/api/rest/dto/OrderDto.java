package com.pcremades.prodor.api.rest.dto;

import java.util.Date;
import java.util.List;

public class OrderDto {

  private Long id;
  private String email;
  private Date createdAt;
  private Double totalPrice;
  private List<ProductDto> productDtoList;

  public OrderDto() {}

  public OrderDto(Long id, String email, Date createdAt, Double totalPrice, List<ProductDto> productDtoList) {
    this.id = id;
    this.email = email;
    this.createdAt = createdAt;
    this.totalPrice = totalPrice;
    this.productDtoList = productDtoList;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<ProductDto> getProductDtoList() {
    return productDtoList;
  }

  public void setProductDtoList(List<ProductDto> productDtoList) {
    this.productDtoList = productDtoList;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }
}
