package com.pcremades.prodor.infrastructure;

import java.util.List;

import com.pcremades.prodor.domain.Order;

public interface OrderDocumentMapper {
  Order toEntity(OrderDocument orderDocument);
  List<Order> toEntity(List<OrderDocument> orderDocumentList);
}
