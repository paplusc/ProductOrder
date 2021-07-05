package com.pcremades.prodor.api.rest.dto;

import java.util.List;

public class PlaceOrderRequest {
  private String email;
  private List<ProductDto> products;

  public PlaceOrderRequest() {}

  public PlaceOrderRequest(String email, List<ProductDto> products) {
    this.email = email;
    this.products = products;
  }

  public String getEmail() {
    return email;
  }

  public List<ProductDto> getProducts() {
    return products;
  }
}
