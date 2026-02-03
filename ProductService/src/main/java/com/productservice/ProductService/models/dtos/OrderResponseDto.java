package com.productservice.ProductService.models.dtos;

import com.productservice.ProductService.models.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private String id;
    private String userId;
    private OrderStatus status;
    private Double totalPrice;
    private List<GenericProductResponseDto> products;
    private String createdAt;
    private String updatedAt;
}

