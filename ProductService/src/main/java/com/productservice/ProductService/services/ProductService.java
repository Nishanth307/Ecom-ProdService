package com.productservice.ProductService.services;

import com.productservice.ProductService.models.dtos.GenericProductRequestDto;
import com.productservice.ProductService.models.dtos.GenericDto;
import com.productservice.ProductService.models.dtos.GenericProductResponseDto;
import com.productservice.ProductService.services.interfaces.IProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class ProductService  {
      private final IProductService productService;
      public ProductService(IProductService productService){
            this.productService = productService ;
      }

      public GenericProductResponseDto getProductById(String id) {
            return productService.getProductById(id);
      }

      public List<GenericProductResponseDto> getAllProducts() {
            return productService.getAllProducts();
      }

      public GenericDto updateProductById(String id, GenericProductRequestDto dto) {
            return productService.updateProductById(id, dto);
      }

      public GenericDto createProduct(GenericProductRequestDto productDto) {
            return productService.createProduct(productDto);
      }

      public GenericProductResponseDto deleteProductById(String id){
            return productService.deleteProductById(id);
      }

      public Page<GenericProductResponseDto> getAllProductsFiltered(int pageNumber) {
            return productService.getAllProductsFiltered(pageNumber);
      }
}
