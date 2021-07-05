package com.pcremades.prodor.api.rest.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pcremades.prodor.domain.Order;

@Component
public class OrderMapperImpl implements OrderMapper {

  private ProductMapper productMapper;

  @Autowired
  public OrderMapperImpl(ProductMapper productMapper) {
    this.productMapper = productMapper;
  }

  @Override
  public Order toEntity(PlaceOrderRequest placeOrderDto) {
    return new Order(null,
               placeOrderDto.getEmail(),
               null,
               null,
               productMapper.toEntity(placeOrderDto.getProducts()));
  }

  @Override
  public OrderDto toDto(Order order) {
    return new OrderDto(order.getId(),
               order.getEmail(),
               order.getCreatedAt(),
               order.getTotalPrice(),
               productMapper.toDto(order.getProducts()));
  }

  @Override
  public List<OrderDto> toDto(List<Order> orders) {
    List<OrderDto> orderDtoList = new ArrayList<>();
    for (Order order : orders) {
      orderDtoList.add(toDto(order));
    }
    return orderDtoList;
  }

  @Override
  public PlaceOrderResponse toPlaceOrderResponse(Order order) {
    return new PlaceOrderResponse(order.getId(), order.getEmail());
  }

}
