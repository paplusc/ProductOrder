package com.pcremades.prodor.api.rest;

import static com.pcremades.prodor.TestUtils.buildListOfProductsDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pcremades.prodor.api.rest.dto.OrderDto;
import com.pcremades.prodor.api.rest.dto.OrderMapper;
import com.pcremades.prodor.api.rest.dto.OrderMapperImpl;
import com.pcremades.prodor.api.rest.dto.PlaceOrderRequest;
import com.pcremades.prodor.api.rest.dto.PlaceOrderResponse;
import com.pcremades.prodor.usecases.OrderUseCase;

public class OrderControllerTest {

  private OrderUseCase orderUseCase = mock(OrderUseCase.class);

  private OrderMapper orderMapper = mock(OrderMapperImpl.class);

  private OrderController orderController = new OrderController(orderUseCase, orderMapper);

  @Nested
  class PlaceOrder {
    @Test
    void ok() {
      PlaceOrderRequest request = new PlaceOrderRequest("test@test.com", buildListOfProductsDto());
      when(orderMapper.toEntity(any())).thenReturn(null);
      when(orderUseCase.placeOrder(any())).thenReturn(null);
      when(orderMapper.toPlaceOrderResponse(any())).thenReturn(new PlaceOrderResponse(1L, "test@test.com"));

      ResponseEntity<PlaceOrderResponse> response = orderController.placeOrder(request);

      assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
    }
  }

  @Nested
  class FindOrders {
    @Test
    void ok() {
      Date from = new GregorianCalendar(2021, Calendar.MAY, 4).getTime();
      Date to = new GregorianCalendar(2021, Calendar.JULY, 4).getTime();
      when(orderMapper.toDto(anyList())).thenReturn(new ArrayList<>());

      ResponseEntity<List<OrderDto>> response = orderController.findOrders(from, to);

      assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
    }
  }
}
