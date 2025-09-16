package com.productservice.ProductService.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GenericProductResponseDto implements Serializable {
    private String id;
    private String title;
    private Integer price;
    private String category;
    private String description;
    private String image;
    private Rating rating;
}

