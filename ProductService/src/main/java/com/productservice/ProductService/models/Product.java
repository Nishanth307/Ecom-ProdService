package com.productservice.ProductService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
public class Product extends BaseModel{
      private String name;
      private String description;
      private String image;
      private Integer price;
      @ManyToOne
      private Category category;
}
