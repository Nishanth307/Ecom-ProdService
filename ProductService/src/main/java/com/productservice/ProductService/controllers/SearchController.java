package com.productservice.ProductService.controllers;

import com.productservice.ProductService.models.dtos.GenericProductResponseDto;
import com.productservice.ProductService.models.datamodels.Product;
import com.productservice.ProductService.repositories.ProductRepository;
import com.productservice.ProductService.services.localDbImpl.ProductDB;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final ProductRepository productRepository;
    private final ProductDB productDB;

    public SearchController(ProductRepository productRepository, ProductDB productDB) {
        this.productRepository = productRepository;
        this.productDB = productDB;
    }

    @GetMapping("/products")
    public List<GenericProductResponseDto> searchProducts(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        List<Product> products = new ArrayList<>();
        
        if (query != null && !query.isEmpty()) {
            products = productRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
        } else {
            products = productRepository.findAll();
        }
        
        // Filter by category
        if (category != null && !category.isEmpty()) {
            products = products.stream()
                .filter(p -> p.getCategory() != null && category.equalsIgnoreCase(p.getCategory().getName()))
                .collect(Collectors.toList());
        }
        
        // Filter by price range
        if (minPrice != null || maxPrice != null) {
            products = products.stream()
                .filter(p -> {
                    if (p.getPrice() == null || p.getPrice().getValue() == null) return false;
                    double price = p.getPrice().getValue();
                    if (minPrice != null && price < minPrice) return false;
                    if (maxPrice != null && price > maxPrice) return false;
                    return true;
                })
                .collect(Collectors.toList());
        }
        
        // Pagination
        int start = page * size;
        int end = Math.min(start + size, products.size());
        List<Product> pagedProducts = start < products.size() 
            ? products.subList(start, end) 
            : new ArrayList<>();
        
        return pagedProducts.stream()
            .map(productDB::convertToGenericProductResponseDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/products/category/{categoryName}")
    public List<GenericProductResponseDto> getProductsByCategory(@PathVariable String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        return products.stream()
            .map(productDB::convertToGenericProductResponseDto)
            .collect(Collectors.toList());
    }
}

