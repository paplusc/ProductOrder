package com.pcremades.prodor.domain;

import java.util.Date;
import java.util.List;

public class Order {
  private final Long id;
  private final String email;
  private final Date createdAt;
  private final Double totalPrice;
  private final List<Product> products;

  public Order(Long id, String email, Date createdAt, Double totalPrice, List<Product> products) {
    this.id = id;
    this.email = email;
    this.createdAt = createdAt;
    this.totalPrice = totalPrice;
    this.products = products;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public List<Product> getProducts() {
    return products;
  }
}
