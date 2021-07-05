package com.pcremades.prodor.api.rest.dto;

public class PlaceOrderResponse {
  private final Long id;
  private final String email;

  public PlaceOrderResponse(Long id, String email) {
    this.id = id;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }
}
