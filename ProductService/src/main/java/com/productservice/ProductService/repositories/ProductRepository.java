package com.productservice.ProductService.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.productservice.ProductService.models.datamodels.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,UUID>{
    Product deleteProductById(UUID id);
}

// List<Product> findByTitle(String title);
// List<Product> findByTitleAndDescription(String title,String description);

// Product findByTitleOrDescription(String Title, String description);
// Product findByPriceLessThan(double price);
// Product findByPriceLessThanEqual(double price);
// Product findByPriceGreaterThan(double price);
// Product findByPriceGreaterThanEqual(double price);
// Product findByPriceBetweenStartPriceAndEndPrice(double startPrice,double endPrice);
// List<Product> findAllByPrice_ValueGreaterThan(Integer x);

//      @Query(value = CustomQueries.query1,nativeQuery = true)
//      List<Product> findAllByPrice_ValueBetween(Integer x,Integer y);
