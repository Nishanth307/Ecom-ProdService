package com.productservice.ProductService.models.datamodels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    @Column(nullable = false,unique = true)
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Product> products;
}
//@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)