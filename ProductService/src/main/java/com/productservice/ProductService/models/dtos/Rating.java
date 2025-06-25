package com.productservice.ProductService.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating {
    private Double rate;
    private Integer count;
}
