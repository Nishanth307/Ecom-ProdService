package com.productservice.ProductService.controllers;

import com.productservice.ProductService.models.dtos.OrderRequestDto;
import com.productservice.ProductService.models.dtos.OrderResponseDto;
import com.productservice.ProductService.models.enums.OrderStatus;
import com.productservice.ProductService.security.JwtObject;
import com.productservice.ProductService.security.TokenValidator;
import com.productservice.ProductService.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final TokenValidator tokenValidator;

    public OrderController(OrderService orderService, TokenValidator tokenValidator) {
        this.orderService = orderService;
        this.tokenValidator = tokenValidator;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody OrderRequestDto orderRequestDto,
            @RequestHeader(value = "token", required = false) String token) {
        
        String userId = extractUserIdFromToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        OrderResponseDto order = orderService.createOrder(orderRequestDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(
            @PathVariable String id,
            @RequestHeader(value = "token", required = false) String token) {
        
        String userId = extractUserIdFromToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            OrderResponseDto order = orderService.getOrderById(id, userId);
            return ResponseEntity.ok(order);
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrders(
            @RequestHeader(value = "token", required = false) String token) {
        
        String userId = extractUserIdFromToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<OrderResponseDto> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(
            @RequestHeader(value = "token", required = false) String token) {
        
        Optional<JwtObject> jwtObject = validateTokenAndCheckAdmin(token);
        if (jwtObject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Check if user has ADMIN role
        if (!hasAdminRole(jwtObject.get())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(
            @PathVariable String id,
            @RequestParam OrderStatus status,
            @RequestHeader(value = "token", required = false) String token) {
        
        String userId = extractUserIdFromToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            OrderResponseDto order = orderService.updateOrderStatus(id, status, userId);
            return ResponseEntity.ok(order);
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(
            @PathVariable String id,
            @RequestHeader(value = "token", required = false) String token) {
        
        String userId = extractUserIdFromToken(token);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            orderService.cancelOrder(id, userId);
            return ResponseEntity.noContent().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private String extractUserIdFromToken(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        Optional<JwtObject> jwtObject = tokenValidator.validateToken(token);
        return jwtObject.map(jwt -> jwt.getUserId().toString()).orElse(null);
    }

    private Optional<JwtObject> validateTokenAndCheckAdmin(String token) {
        if (token == null || token.isEmpty()) {
            return Optional.empty();
        }
        return tokenValidator.validateToken(token);
    }

    private boolean hasAdminRole(JwtObject jwtObject) {
        if (jwtObject.getRoles() == null) {
            return false;
        }
        return jwtObject.getRoles().stream()
            .anyMatch(role -> "ADMIN".equalsIgnoreCase(role.getRole()));
    }
}

