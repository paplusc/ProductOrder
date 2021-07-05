package com.pcremades.prodor.api.rest.dto;

public class CreateProductRequest {
  private final String name;
  private final Double price;

  public CreateProductRequest( String name, Double price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public Double getPrice() {
    return price;
  }
}
