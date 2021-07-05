package com.pcremades.prodor.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pcremades.prodor.domain.Order;

@Component
public class OrderDocumentMapperImpl implements OrderDocumentMapper {

  private ProductDocumentMapper productDocumentMapper;

  @Autowired
  public OrderDocumentMapperImpl(ProductDocumentMapper productDocumentMapper) {
    this.productDocumentMapper = productDocumentMapper;
  }

  @Override
  public Order toEntity(OrderDocument orderDocument) {
    return new Order(orderDocument.getId(),
               orderDocument.getEmail(),
               orderDocument.getCreatedAt(),
               orderDocument.getTotalPrice(),
               productDocumentMapper.toEntity(orderDocument.getProducts()));
  }

  @Override
  public List<Order> toEntity(List<OrderDocument> orderDocumentList) {
    List<Order> orders = new ArrayList<>();
    for (OrderDocument orderDocument : orderDocumentList) {
      orders.add(toEntity(orderDocument));
    }
    return orders;
  }
}
