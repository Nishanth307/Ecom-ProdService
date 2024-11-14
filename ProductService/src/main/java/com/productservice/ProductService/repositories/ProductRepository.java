package com.productservice.ProductService.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.productservice.ProductService.models.Product;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,UUID>{
      /*
       
      List<Product> findByTitle(String title);
      List<Product> findByTitleAndDescription(String title,String description);
      List<Product> findAllByPrice_ValueGreaterThan(Integer x);
      
      @Query(value = "Select * from product",nativeQuery = true)
      List<Product> findAllByPrice_ValueBetween(Integer x,Integer y);
      */
}
