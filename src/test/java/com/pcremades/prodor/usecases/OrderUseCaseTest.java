package com.pcremades.prodor.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.pcremades.prodor.TestUtils;
import com.pcremades.prodor.domain.Order;
import com.pcremades.prodor.infrastructure.OrderDocument;
import com.pcremades.prodor.infrastructure.OrderDocumentMapper;
import com.pcremades.prodor.infrastructure.OrderDocumentMapperImpl;
import com.pcremades.prodor.infrastructure.OrderRepository;
import com.pcremades.prodor.infrastructure.ProductRepository;

public class OrderUseCaseTest {
  private OrderRepository orderRepository = mock(OrderRepository.class);
  private ProductRepository productRepository = mock(ProductRepository.class);
  private OrderDocumentMapper orderMapper = mock(OrderDocumentMapperImpl.class);
  private OrderUseCase orderUseCase = new OrderUseCase(orderRepository, productRepository, orderMapper);

  @Test
  void placeOrder() {
    final Order order = TestUtils.buildOrder();
    when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
    when(orderRepository.save(any())).thenReturn(new OrderDocument());
    when(orderMapper.toEntity(any(OrderDocument.class))).thenReturn(TestUtils.buildOrder());

    final Order response = orderUseCase.placeOrder(order);

    final SoftAssertions softly = new SoftAssertions();
    softly.assertThat(response.getId()).as("id").isEqualTo(1L);
    softly.assertThat(response.getEmail()).as("email").isEqualTo("test@test.com");
    softly.assertThat(response.getCreatedAt()).as("createdAt").isInstanceOf(Date.class);
    softly.assertThat(response.getTotalPrice()).as("totalPrice").isEqualTo(100.10);
    softly.assertThat(response.getProducts()).as("products").isNotEmpty();
  }

  @Test
  void findOrders() {
    final Date from = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
    final Date to = new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime();
    final List<Order> expected = TestUtils.buildListOfOrders();
    when(orderRepository.findOrders(from, to)).thenReturn(new ArrayList<>());
    when(orderMapper.toEntity(anyList())).thenReturn(expected);

    final List<Order> response = orderUseCase.findOrders(from, to);

    assertThat(response).extracting("id", "email", "createdAt", "totalPrice", "products")
               .contains(
                          tuple(expected.get(0).getId(),
                                     expected.get(0).getEmail(),
                                     expected.get(0).getCreatedAt(),
                                     expected.get(0).getTotalPrice(),
                                     expected.get(0).getProducts()),
                          tuple(expected.get(1).getId(),
                                     expected.get(1).getEmail(),
                                     expected.get(1).getCreatedAt(),
                                     expected.get(1).getTotalPrice(),
                                     expected.get(1).getProducts())
               );
  }
}
