package com.pcremades.prodor.infrastructure;

import static com.pcremades.prodor.TestUtils.buildListOfOrderDocument;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.pcremades.prodor.TestUtils;
import com.pcremades.prodor.domain.Order;

public class OrderDocumentMapperTest {

  private ProductDocumentMapper productDocumentMapper = mock(ProductDocumentMapperImpl.class);

  private OrderDocumentMapper orderDocumentMapper = new OrderDocumentMapperImpl(productDocumentMapper);

  @Nested
  class ToEntity {
    @Test
    void whenOrderDocument() {
      final OrderDocument document = new OrderDocument();
      final Date date = new GregorianCalendar(2021, Calendar.JANUARY,1).getTime();
      document.setId(1L);
      document.setEmail("test@test.com");
      document.setCreatedAt(date);
      document.setTotalPrice(999.99);
      document.setProducts(TestUtils.buildListOfDocumentProducts());
      when(productDocumentMapper.toEntity(anyList())).thenReturn(new ArrayList<>());

      final Order response = orderDocumentMapper.toEntity(document);

      final SoftAssertions softly = new SoftAssertions();
      softly.assertThat(response.getId()).as("id").isEqualTo(1L);
      softly.assertThat(response.getEmail()).as("email").isEqualTo("test@test.com");
      softly.assertThat(response.getCreatedAt()).as("createdAt").isEqualTo(date);
      softly.assertThat(response.getTotalPrice()).as("totalPrice").isEqualTo(999.99);
      softly.assertThat(response.getProducts()).as("products").isEmpty();
    }
    @Test
    void whenListOrderDocument() {
      final List<OrderDocument> orders = buildListOfOrderDocument();
      when(productDocumentMapper.toEntity(anyList())).thenReturn(new ArrayList<>());

      final List<Order> response = orderDocumentMapper.toEntity(orders);

      assertThat(response).extracting("id", "email", "createdAt", "totalPrice")
                 .contains(
                            tuple(1L,
                                       "test1@test.com",
                                       orders.get(0).getCreatedAt(),
                                       100.10),
                            tuple(2L,
                                       "test2@test.com",
                                       orders.get(1).getCreatedAt(),
                                       200.20)
                 );
    }
  }
}
