package com.productservice.ProductService.models.FakeStore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductRequestDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
}
