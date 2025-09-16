package com.productservice.ProductService.models.datamodels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    @Column(nullable = false,unique = true)
    private String name;
    private String title;
    private String image;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private Price price;
    @OneToOne(cascade = CascadeType.ALL)
    private Rating rating;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},optional = false)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Order> orders;
}
