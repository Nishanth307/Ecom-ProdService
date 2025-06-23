package com.productservice.ProductService.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericDto {
    private String id;
    private String title;
    private Integer price;
    private String category;
    private String description;
    private String image;
}
