package com.productservice.ProductService.services;

import com.productservice.ProductService.dtos.GenericProductDto;
import com.productservice.ProductService.exceptions.ProductNotFoundException;
import com.productservice.ProductService.models.Product;
import com.productservice.ProductService.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Primary will
@Service
public class SelfProductService implements ProductService2{

    private ProductRepository productRepository;


    @Override
    public GenericProductDto getProductById(UUID id) throws ProductNotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();

        //conversion
        //genericProductDto.setId(product.getId());
        //genericProductDto.setTitle(product.getTitle());
        return  null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return List.of();
    }

    @Override
    public GenericProductDto deleteProduct(UUID id) throws ProductNotFoundException {
        return null;
    }



    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public GenericProductDto updateProduct(UUID id, GenericProductDto genericProductDto) throws ProductNotFoundException {
        return null;
    }


}
