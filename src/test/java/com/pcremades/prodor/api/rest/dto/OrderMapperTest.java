package com.pcremades.prodor.api.rest.dto;

import static com.pcremades.prodor.TestUtils.buildListOfOrders;
import static com.pcremades.prodor.TestUtils.buildListOfProductsDto;
import static com.pcremades.prodor.TestUtils.buildOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.pcremades.prodor.domain.Order;
import com.pcremades.prodor.domain.Product;

public class OrderMapperTest {

  private final ProductMapper productMapper = mock(ProductMapperImpl.class);

  private final OrderMapper orderMapper = new OrderMapperImpl(productMapper);

  @Nested
  class ToEntity {
    @Test
    void whenPlaceOrderRequest() {
      final PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest("test@test.com", buildListOfProductsDto());
      when(productMapper.toEntity(anyList())).thenReturn(new ArrayList<>());

      final Order response = orderMapper.toEntity(placeOrderRequest);

      final SoftAssertions softly = new SoftAssertions();
      softly.assertThat(response.getId()).as("id").isNull();
      softly.assertThat(response.getEmail()).as("email").isEqualTo("test@test.com");
      softly.assertThat(response.getCreatedAt()).as("createdAt").isNull();
      softly.assertThat(response.getTotalPrice()).as("totalPrice").isNull();
      softly.assertThat(response.getProducts()).as("products").isEmpty();
    }
  }

  @Nested
  class ToDto {
    @Test
    void whenOrder() {
      final Order order = buildOrder();
      when(productMapper.toDto(anyList())).thenReturn(new ArrayList<>());

      final OrderDto response = orderMapper.toDto(order);

      final SoftAssertions softly = new SoftAssertions();
      softly.assertThat(response.getId()).as("id").isNull();
      softly.assertThat(response.getEmail()).as("email").isEqualTo("test@test.com");
      softly.assertThat(response.getCreatedAt()).as("createdAt").isNull();
      softly.assertThat(response.getTotalPrice()).as("totalPrice").isNull();
      softly.assertThat(response.getProductDtoList()).as("products").isEmpty();
    }
    @Test
    void whenListOfOrders() {
      final List<Order> orders = buildListOfOrders();
      when(productMapper.toDto(any(Product.class))).thenReturn(null);

      final List<OrderDto> response = orderMapper.toDto(orders);

      assertThat(response).extracting("id", "email", "createdAt", "totalPrice")
                 .contains(
                            tuple(1L,
                                       "test@test.com",
                                       orders.get(0).getCreatedAt(),
                                       100.10),
                            tuple(2L,
                                       "test2@test.com",
                                       orders.get(1).getCreatedAt(),
                                       200.20)
                 );
    }
    @Test
    void whenPlaceOrderResponse() {
      final Order order = buildOrder();

      final PlaceOrderResponse response = orderMapper.toPlaceOrderResponse(order);

      final SoftAssertions softly = new SoftAssertions();
      softly.assertThat(response.getId()).isEqualTo(1L);
      softly.assertThat(response.getEmail()).isEqualTo("test@test.com");
    }
  }
}
