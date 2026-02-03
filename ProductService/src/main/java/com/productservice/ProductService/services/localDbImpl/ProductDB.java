package com.productservice.ProductService.services.localDbImpl;

import com.productservice.ProductService.exceptions.ProductNotFoundException;
import com.productservice.ProductService.models.dtos.GenericProductRequestDto;
import com.productservice.ProductService.models.datamodels.Product;
import com.productservice.ProductService.models.dtos.GenericDto;
import com.productservice.ProductService.models.dtos.GenericProductResponseDto;
import com.productservice.ProductService.models.datamodels.Rating;
import com.productservice.ProductService.repositories.ProductRepository;
import com.productservice.ProductService.services.interfaces.IProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Component("productLocalDb")
public class ProductDB implements IProductService {
    private final ProductRepository productRepository;
    public ProductDB(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable(value = "products",key = "#id")
    public GenericProductResponseDto getProductById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Product> productOptional = productRepository.findById(uuid);
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("product not found for uuid:"+uuid);
        }
        Product product = productOptional.get();
        return convertToGenericProductResponseDto(product);
    }

    @Override
    public List<GenericProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductResponseDto> dtos = new ArrayList<>();
        for (Product product:products) {
            dtos.add(convertToGenericProductResponseDto(product));
        }
        return dtos;
    }

    @Override
    public Page<GenericProductResponseDto> getAllProductsFiltered(int pageNumber) {
        Sort sort = Sort.by("title").and(Sort.by("rating"));
        Page<Product> products = productRepository.findAll(PageRequest.of(pageNumber,3, sort));
        return products.map(this::convertToGenericProductResponseDto);
    }

    @Override
    public GenericProductResponseDto deleteProductById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Product> productOptional = productRepository.findById(uuid);
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("product not found for uuid:"+uuid);
        }
        Product product = productOptional.get();
        GenericProductResponseDto responseDto = convertToGenericProductResponseDto(product);
        productRepository.deleteById(uuid);
        return responseDto;
    }

    @Override
    public GenericDto createProduct(GenericProductRequestDto productDto) {
        Product product = convertToProduct(productDto);
        productRepository.save(product);
        return convertToGenericDto(productDto);
    }

    @Override
    public GenericDto updateProductById(String id, GenericProductRequestDto productDto) {
        UUID uuid = UUID.fromString(id);
        Optional<Product> productOptional = productRepository.findById(uuid);
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("product not found for uuid:"+uuid);
        }
        Product existingProduct = productOptional.get();
        
        // Update existing product with new values
        existingProduct.setName(productDto.getName());
        existingProduct.setTitle(productDto.getTitle());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setImage(productDto.getImage());
        existingProduct.setOrders(productDto.getOrders());
        existingProduct.setPrice(productDto.getPrice());
        
        // Update rating
        if (productDto.getRating() != null) {
            if (existingProduct.getRating() != null) {
                existingProduct.getRating().setCount(productDto.getRating().getCount());
                existingProduct.getRating().setRate(productDto.getRating().getRate());
            } else {
                Rating rating = new Rating();
                rating.setCount(productDto.getRating().getCount());
                rating.setRate(productDto.getRating().getRate());
                existingProduct.setRating(rating);
            }
        }
        
        productRepository.save(existingProduct);
        return convertToGenericDto(productDto);
    }

    private Product convertToProduct(GenericProductRequestDto dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setImage(dto.getImage());
        product.setOrders(dto.getOrders());
        product.setPrice(dto.getPrice());

        // Set rating if provided
        if (dto.getRating() != null) {
            Rating rating = new Rating();
            rating.setCount(dto.getRating().getCount());
            rating.setRate(dto.getRating().getRate());
            product.setRating(rating);
        }

        return product;
    }

    private GenericDto convertToGenericDto(GenericProductRequestDto requestDto){
        GenericDto dto = new GenericDto();
        dto.setName(requestDto.getName());
        dto.setTitle(requestDto.getTitle());
        if (requestDto.getPrice() != null && requestDto.getPrice().getValue() != null) {
            dto.setPrice(requestDto.getPrice().getValue());
        }
        if (requestDto.getCategory() != null) {
            dto.setCategory(requestDto.getCategory().getName());
        }
        dto.setDescription(requestDto.getDescription());
        dto.setImage(requestDto.getImage());
        return dto;
    }

    public GenericProductResponseDto convertToGenericProductResponseDto(Product product){
        GenericProductResponseDto dto = new GenericProductResponseDto();
        dto.setId(product.getId().toString());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setCategory(product.getCategory() != null ? product.getCategory().getName() : null);
        
        // Set price
        if (product.getPrice() != null && product.getPrice().getValue() != null) {
            dto.setPrice(product.getPrice().getValue().intValue());
        }
        
        // Set rating
        if (product.getRating() != null) {
            com.productservice.ProductService.models.dtos.Rating ratingDto = 
                new com.productservice.ProductService.models.dtos.Rating();
            ratingDto.setRate(product.getRating().getRate());
            ratingDto.setCount(product.getRating().getCount());
            dto.setRating(ratingDto);
        }
        
        return dto;
    }

}
