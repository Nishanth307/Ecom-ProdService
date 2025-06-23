package com.productservice.ProductService.services.localDb;

import com.productservice.ProductService.models.FakeStore.GenericProductRequestDto;
import com.productservice.ProductService.models.datamodels.Product;
import com.productservice.ProductService.models.dtos.GenericDto;
import com.productservice.ProductService.models.dtos.GenericProductResponseDto;
import com.productservice.ProductService.repositories.ProductRepository;
import com.productservice.ProductService.services.interfaces.IProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component("productLocalDb")
public class ProductDB implements IProductService {
    private final ProductRepository productRepository;
    public ProductDB(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductResponseDto getProductById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Product> product = productRepository.findById(uuid);

        // to product

        return null;
    }

    @Override
    public List<GenericProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return List.of();
    }

    @Override
    public GenericProductResponseDto deleteProductById(String id) {
        UUID uuid = UUID.fromString(id);
        Product product= productRepository.deleteProductById(uuid);
        return null;
    }

    @Override
    public GenericDto createProduct(GenericProductRequestDto productDto) {
        return null;
    }

    @Override
    public GenericDto updateProductById(String id, GenericProductRequestDto productDto) {
        UUID uuid = UUID.fromString(id);
        Product product = new Product();
        productRepository.save(product);
        return null;
    }
}

// normally we don't have to send response
