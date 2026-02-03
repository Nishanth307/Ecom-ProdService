package com.productservice.ProductService.services;

import com.productservice.ProductService.exceptions.ProductNotFoundException;
import com.productservice.ProductService.models.datamodels.Order;
import com.productservice.ProductService.models.datamodels.Product;
import com.productservice.ProductService.models.dtos.GenericProductResponseDto;
import com.productservice.ProductService.models.dtos.OrderRequestDto;
import com.productservice.ProductService.models.dtos.OrderResponseDto;
import com.productservice.ProductService.models.enums.OrderStatus;
import com.productservice.ProductService.repositories.OrderRepository;
import com.productservice.ProductService.repositories.ProductRepository;
import com.productservice.ProductService.services.localDbImpl.ProductDB;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductDB productDB;

    public OrderService(OrderRepository orderRepository, 
                       ProductRepository productRepository,
                       ProductDB productDB) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productDB = productDB;
    }

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto, String userId) {
        if (orderRequestDto == null || orderRequestDto.getProductIds() == null || orderRequestDto.getProductIds().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one product");
        }

        // Fetch products
        List<Product> products = new ArrayList<>();
        for (String productId : orderRequestDto.getProductIds()) {
            try {
                UUID productUuid = UUID.fromString(productId);
                Product product = productRepository.findById(productUuid)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found: " + productId));
                products.add(product);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid product ID format: " + productId);
            }
        }

        // Create order
        Order order = new Order();
        order.setUser(userId != null ? userId : orderRequestDto.getUserId());
        order.setStatus(OrderStatus.PLACED);
        order.setTotalPrice(orderRequestDto.getTotalPrice());
        order.setProducts(products);

        Order savedOrder = orderRepository.save(order);
        return convertToOrderResponseDto(savedOrder);
    }

    public OrderResponseDto getOrderById(String orderId, String userId) {
        UUID uuid = UUID.fromString(orderId);
        Order order = orderRepository.findById(uuid)
            .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        // Check if user owns the order
        if (userId != null && !order.getUser().equals(userId)) {
            throw new SecurityException("Access denied: You don't have permission to view this order");
        }

        return convertToOrderResponseDto(order);
    }

    public List<OrderResponseDto> getOrdersByUser(String userId) {
        List<Order> orders = orderRepository.findByUser(userId);
        return orders.stream()
            .map(this::convertToOrderResponseDto)
            .collect(Collectors.toList());
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
            .map(this::convertToOrderResponseDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponseDto updateOrderStatus(String orderId, OrderStatus status, String userId) {
        UUID uuid = UUID.fromString(orderId);
        Order order = orderRepository.findById(uuid)
            .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        // Check if user owns the order or is admin
        if (userId != null && !order.getUser().equals(userId)) {
            throw new SecurityException("Access denied: You don't have permission to update this order");
        }

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return convertToOrderResponseDto(updatedOrder);
    }

    @Transactional
    public void cancelOrder(String orderId, String userId) {
        UUID uuid = UUID.fromString(orderId);
        Order order = orderRepository.findById(uuid)
            .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        if (userId != null && !order.getUser().equals(userId)) {
            throw new SecurityException("Access denied: You don't have permission to cancel this order");
        }

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot cancel a delivered order");
        }

        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);
    }

    private OrderResponseDto convertToOrderResponseDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId().toString());
        dto.setUserId(order.getUser());
        dto.setStatus(order.getStatus());
        dto.setTotalPrice(order.getTotalPrice());
        
        if (order.getProducts() != null && !order.getProducts().isEmpty()) {
            List<GenericProductResponseDto> productDtos = order.getProducts().stream()
                .map(productDB::convertToGenericProductResponseDto)
                .collect(Collectors.toList());
            dto.setProducts(productDtos);
        } else {
            dto.setProducts(new ArrayList<>());
        }
        
        if (order.getCreatedAt() != null) {
            dto.setCreatedAt(order.getCreatedAt().toString());
        }
        if (order.getLastUpdatedAt() != null) {
            dto.setUpdatedAt(order.getLastUpdatedAt().toString());
        }
        
        return dto;
    }
}

