package com.productservice.ProductService.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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
