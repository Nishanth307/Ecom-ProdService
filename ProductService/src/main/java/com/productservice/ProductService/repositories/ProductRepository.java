package com.productservice.ProductService.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.productservice.ProductService.models.datamodels.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,UUID>{
    Product deleteProductById(UUID id);
    
    // Search functionality
    List<Product> findByTitleContainingIgnoreCase(String title);
    List<Product> findByDescriptionContainingIgnoreCase(String description);
    List<Product> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    
    @Query("SELECT p FROM Product p WHERE p.category.name = :categoryName")
    List<Product> findByCategoryName(@Param("categoryName") String categoryName);
    
    @Query("SELECT p FROM Product p WHERE p.price.value BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
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
