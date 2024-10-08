package com.productservice.ProductService.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private Integer price;
    private String category;
    private String description;
    private String image;
}
