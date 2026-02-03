package com.productservice.ProductService.repositories;

import com.productservice.ProductService.models.datamodels.Order;
import com.productservice.ProductService.models.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUser(String userId);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByUserAndStatus(String userId, OrderStatus status);
}