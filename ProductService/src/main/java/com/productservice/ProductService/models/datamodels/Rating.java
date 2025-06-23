package com.productservice.ProductService.models.datamodels;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rating extends BaseModel{
    private Double rate;
    private Integer count;
}
