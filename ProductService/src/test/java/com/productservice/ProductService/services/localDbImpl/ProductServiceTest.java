package com.productservice.ProductService.services.localDbImpl;

import com.productservice.ProductService.models.datamodels.Product;
import com.productservice.ProductService.repositories.ProductRepository;
import com.productservice.ProductService.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this); //creates auto closable resources for each test method
    }

    @Test
    public void testgetProductByIdSuccess(){
        // arrange
        String testId = "TestProductId";
//        Product mockProduct
        // act

        // assert

    }
}
//@SpringBootTest
//public class ProductDemoNotWorkingTest {
//    @MockBean
//    ProductRepository productRepository;
//
//    ProductDB productDB;
//    public ProductDemoNotWorkingTest(ProductDB productDB){
//        this.productDB = productDB;
//    }

//}