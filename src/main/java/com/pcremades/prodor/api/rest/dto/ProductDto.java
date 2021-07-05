package com.pcremades.prodor.api.rest.dto;

public class ProductDto {
  private final Long id;
  private final String name;
  private final Double price;

  public ProductDto(Long id, String name, Double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Double getPrice() {
    return price;
  }
}
