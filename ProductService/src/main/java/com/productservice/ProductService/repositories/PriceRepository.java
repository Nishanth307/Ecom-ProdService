package com.productservice.ProductService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.productservice.ProductService.models.Price;

public interface PriceRepository extends JpaRepository<Price,UUID>{
}
