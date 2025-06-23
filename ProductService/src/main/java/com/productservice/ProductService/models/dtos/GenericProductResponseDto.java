package com.productservice.ProductService.models.dtos;

import com.productservice.ProductService.models.FakeStore.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductResponseDto {
    private String id;
    private String title;
    private Integer price;
    private String category;
    private String description;
    private String image;
    private Rating rating;
}

