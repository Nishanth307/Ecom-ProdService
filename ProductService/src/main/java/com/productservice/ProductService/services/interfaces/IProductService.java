package com.productservice.ProductService.services.interfaces;

import java.util.List;

import com.productservice.ProductService.models.dtos.GenericProductRequestDto;
import com.productservice.ProductService.models.dtos.GenericDto;
import com.productservice.ProductService.models.dtos.GenericProductResponseDto;

public interface IProductService {
        public GenericProductResponseDto getProductById(String id);
        public List<GenericProductResponseDto> getAllProducts();
        public GenericProductResponseDto deleteProductById(String id);
        public GenericDto createProduct(GenericProductRequestDto productDto);
        public GenericDto updateProductById(String id, GenericProductRequestDto productDto);
}