package com.pcremades.prodor.api.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcremades.prodor.api.rest.dto.OrderDto;
import com.pcremades.prodor.api.rest.dto.OrderMapper;
import com.pcremades.prodor.api.rest.dto.PlaceOrderRequest;
import com.pcremades.prodor.api.rest.dto.PlaceOrderResponse;
import com.pcremades.prodor.domain.Order;
import com.pcremades.prodor.usecases.OrderUseCase;

@RestController
public class OrderController {

  private OrderUseCase orderUseCase;
  private OrderMapper mapper;

  @Autowired
  public OrderController(OrderUseCase orderUseCase, OrderMapper mapper) {
    this.orderUseCase = orderUseCase;
    this.mapper = mapper;
  }

  @PostMapping("/placeOrder")
  public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody final PlaceOrderRequest request) {
    final Order order = orderUseCase.placeOrder(mapper.toEntity(request));
    final PlaceOrderResponse response = mapper.toPlaceOrderResponse(order);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/findOrders")
  public ResponseEntity<List<OrderDto>> findOrders(@RequestParam("from")
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date from,
                                                   @RequestParam("to")
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date to) {
    List<Order> orders = orderUseCase.findOrders(from, to);
    List<OrderDto> response = mapper.toDto(orders);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
