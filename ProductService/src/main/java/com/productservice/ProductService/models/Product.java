package com.productservice.ProductService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
      private String name;
      private String description;
      private String image;
      private Integer price;
      private Category category;
}
