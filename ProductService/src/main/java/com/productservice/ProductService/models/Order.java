package com.productservice.ProductService.models;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "orders")
public class Order extends BaseModel{
      @ManyToMany
      private List<Product> products;

}
