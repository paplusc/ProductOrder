package com.pcremades.prodor.infrastructure;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDocument, Long> {

  @Query(value = "select * from orders o where o.created_at between ?1 and ?2",
  nativeQuery = true)
  List<OrderDocument> findOrders(Date from, Date to);
}
