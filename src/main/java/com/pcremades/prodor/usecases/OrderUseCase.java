package com.pcremades.prodor.usecases;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcremades.prodor.domain.Order;
import com.pcremades.prodor.domain.Product;
import com.pcremades.prodor.infrastructure.OrderDocument;
import com.pcremades.prodor.infrastructure.OrderDocumentMapper;
import com.pcremades.prodor.infrastructure.OrderRepository;
import com.pcremades.prodor.infrastructure.ProductDocument;
import com.pcremades.prodor.infrastructure.ProductRepository;

@Service
public class OrderUseCase {

  private OrderRepository orderRepository;
  private ProductRepository productRepository;
  private OrderDocumentMapper orderMapper;

  @Autowired
  public OrderUseCase(OrderRepository orderRepository, ProductRepository productRepository, OrderDocumentMapper orderMapper) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.orderMapper = orderMapper;
  }

  public Order placeOrder(Order order) {
    OrderDocument orderDocument = validateProducts(order.getProducts());
    orderDocument.setEmail(order.getEmail());
    orderDocument.setTotalPrice(calculateTotalPrice(orderDocument.getProducts()));
    orderDocument.setCreatedAt(new Date());
    return orderMapper.toEntity(orderRepository.save(orderDocument));
  }

  public List<Order> findOrders(Date from, Date to) {
    List<OrderDocument> orderDocuments = orderRepository.findOrders(from, to);
    return orderMapper.toEntity(orderDocuments);
  }

  private Double calculateTotalPrice(List<ProductDocument> products) {
    Double totalPrice = 0.00;
    for (ProductDocument product : products) {
      totalPrice += product.getPrice();
    }
    return totalPrice;
  }

  private OrderDocument validateProducts(List<Product> products) {
    OrderDocument orderDocument = new OrderDocument();
    for (Product product : products) {
      Optional<ProductDocument> productDocument = productRepository.findById(product.getId());
      productDocument.ifPresent(document -> orderDocument.getProducts().add(document));
    }
    return orderDocument;
  }
}
