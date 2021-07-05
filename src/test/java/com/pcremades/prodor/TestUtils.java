package com.pcremades.prodor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.pcremades.prodor.api.rest.dto.ProductDto;
import com.pcremades.prodor.domain.Order;
import com.pcremades.prodor.domain.Product;
import com.pcremades.prodor.infrastructure.OrderDocument;
import com.pcremades.prodor.infrastructure.ProductDocument;

public class TestUtils {

  private TestUtils() {}

  public static List<ProductDto> buildListOfProductsDto() {
    final List<ProductDto> list = new ArrayList<>();
    list.add(new ProductDto(1L, "name1", 10.00));
    list.add(new ProductDto(2L, "name2", 20.00));
    list.add(new ProductDto(3L, "name3", 30.00));
    return list;
  }

  public static List<Product> buildListOfProducts() {
    final List<Product> list = new ArrayList<>();
    list.add(new Product(1L, "name1", 10.00));
    list.add(new Product(2L, "name2", 20.00));
    list.add(new Product(3L, "name3", 30.00));
    return list;
  }

  public static List<ProductDocument> buildListOfDocumentProducts() {
    final List<ProductDocument> list = new ArrayList<>();
    final ProductDocument document1 = new ProductDocument();
    document1.setId(1L);
    document1.setName("name1");
    document1.setPrice(10.00);
    final ProductDocument document2 = new ProductDocument();
    document2.setId(2L);
    document2.setName("name2");
    document2.setPrice(20.00);
    final ProductDocument document3 = new ProductDocument();
    document3.setId(3L);
    document3.setName("name3");
    document3.setPrice(30.00);
    list.add(document1);
    list.add(document2);
    list.add(document3);
    return list;
  }

  public static Order buildOrder() {
    return new Order(1L,
               "test@test.com",
               new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime(),
               100.10,
               buildListOfProducts()
    );
  }

  public static List<Order> buildListOfOrders() {
    Order order1 = new Order(1L,
               "test@test.com",
               new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime(),
               100.10,
               buildListOfProducts()
    );
    Order order2 = new Order(2L,
               "test2@test.com",
               new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime(),
               200.20,
               buildListOfProducts()
    );
    return List.of(order1, order2);
  }

  public static List<OrderDocument> buildListOfOrderDocument() {
    OrderDocument order1 = new OrderDocument();
    order1.setId(1L);
    order1.setEmail("test1@test.com");
    order1.setCreatedAt(new GregorianCalendar(2021, 0, 1).getTime());
    order1.setTotalPrice(100.10);
    order1.setProducts(buildListOfDocumentProducts());

    OrderDocument order2 = new OrderDocument();
    order2.setId(2L);
    order2.setEmail("test2@test.com");
    order2.setCreatedAt(new GregorianCalendar(2021, 1, 1).getTime());
    order2.setTotalPrice(200.20);
    order2.setProducts(buildListOfDocumentProducts());
    return List.of(order1, order2);
  }
}
