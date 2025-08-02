package com.productservice.ProductService.models.dtos;

import com.productservice.ProductService.models.datamodels.Category;
import com.productservice.ProductService.models.datamodels.Order;
import com.productservice.ProductService.models.datamodels.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class GenericProductRequestDto {
    private String id;
    private String name;
    private String title;
    private Price price;
    private Rating rating;
    private String description;
    private Category category;
    private String image;
    private List<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenericProductRequestDto that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(category, that.category) &&
                Objects.equals(price, that.price) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, category, price, rating);
    }

}

