package com.productservice.ProductService.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private String userId;
    private List<String> productIds;
    private Double totalPrice;
}

