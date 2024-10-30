package com.productservice.ProductService.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
      @Column(nullable = false, unique = true)
      private String name;

      @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
      private List<Product> products;
}
