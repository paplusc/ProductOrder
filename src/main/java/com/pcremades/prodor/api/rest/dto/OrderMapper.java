package com.pcremades.prodor.api.rest.dto;

import java.util.List;

import com.pcremades.prodor.domain.Order;

public interface OrderMapper {
  Order toEntity(PlaceOrderRequest placeOrderDto);
  OrderDto toDto(Order order);
  List<OrderDto> toDto(List<Order> orders);
  PlaceOrderResponse toPlaceOrderResponse(Order order);
}
