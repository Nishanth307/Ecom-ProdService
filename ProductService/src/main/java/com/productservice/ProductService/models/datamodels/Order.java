package com.productservice.ProductService.models.datamodels;

import com.productservice.ProductService.models.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "orders")
public class Order extends BaseModel{
    private String user;
    private OrderStatus status;
    private Double totalPrice;
    @ManyToMany(mappedBy = "orders")
    private List<Product> products;
}
