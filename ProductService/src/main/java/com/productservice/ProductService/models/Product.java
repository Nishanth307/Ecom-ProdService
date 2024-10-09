package com.productservice.ProductService.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product extends BaseModel{
      private String name;
      private String description;
      private String image;
      private Integer price;
      private Category category;
}
