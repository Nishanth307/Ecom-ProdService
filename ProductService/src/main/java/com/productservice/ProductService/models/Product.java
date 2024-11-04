package com.productservice.ProductService.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
      private String title;
      private String description;
      private String image;
      
      @ManyToOne(optional = false)
      private Category category;
      
      //@JoinColumn(nullable = false)
      @OneToOne(optional = false,cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
      private Price price;
}
