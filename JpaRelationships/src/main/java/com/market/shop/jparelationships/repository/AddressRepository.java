package com.market.shop.jparelationships.repository;

import com.market.shop.jparelationships.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Long, Order> {
    @Query("SELECT o from Order o WHERE o.status=:status")
    Iterable<Order> findAllByStatusOrderByDateCreated(@Param(value = "status") String status);
}
