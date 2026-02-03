package com.productservice.ProductService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.ProductService.client.UserServiceClient;
import com.productservice.ProductService.models.datamodels.Category;
import com.productservice.ProductService.models.datamodels.Price;
import com.productservice.ProductService.models.dtos.GenericDto;
import com.productservice.ProductService.models.dtos.GenericProductRequestDto;
import com.productservice.ProductService.models.dtos.GenericProductResponseDto;
import com.productservice.ProductService.models.dtos.Rating;
import com.productservice.ProductService.models.dtos.SessionStatus;
import com.productservice.ProductService.services.interfaces.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class) // testing api calls
public class ProductControllerIT {
    @Autowired
    private MockMvc mockMvc; //mock api calls

    @MockBean(name = "productFakeStore")
    private IProductService productService;

    @MockBean
    private UserServiceClient userServiceClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProductsReturnEmptyListWhenNoProductsAvailable() throws Exception {
        List<GenericProductResponseDto> emptyProductListResponse = new ArrayList<>();
        when(productService.getAllProducts()).thenReturn(emptyProductListResponse);
        when(userServiceClient.validate(any())).thenReturn(SessionStatus.ACTIVE.name());

        mockMvc.perform(get("/products")
                        .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsInJvbGVzIjpbIlVTRVIiXX0.test"))
                .andExpect(status().is(200))
                .andExpect(content().string("[]"));
    }
//
//    @Test
//    public void testCreateOrderRunsSuccessfully() throws Exception {
//
//        //Arrange
//        OrderRequestDto requestDto = new OrderRequestDto();
//        requestDto.setCustomerId(2L);
//        requestDto.setTotalAmount(69D);
//
//        Order order = new Order();
//        order.setCustomerId(2L);
//        order.setTotalAmount(69D);
//        order.setStatus(OrderStatus.CREATED);
//        order.setId(UUID.randomUUID());
//        order.setCreatedAt(new Date());
//        order.setLastUpdatedAt(new Date());
//
//        when(orderService.createOrder(2L,69D)).thenReturn(order);
//
//        // Act & Assert
//        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(requestDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().string(objectMapper.writeValueAsString(order)));
//    }
//
//    @Test
//    public void testGetOrderByIdWithExistingOrder() throws Exception {
//        // Arrange
//        Order order = new Order();
//        order.setId(25L);
//        order.setStatus(OrderStatus.INTRANSIT);
//        order.setCustomerId(5000L);
//
//        when(orderService.getOrderById(25L)).thenReturn(order);
//
//        // Act & Assert
//        mockMvc.perform(get("/order/25"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(25))
//                .andExpect(jsonPath("$.status").value("INTRANSIT"))
//                .andExpect(jsonPath("$.customerId").value(5000L));
//    }
//
//    @Test
//    public void testGetOrders() throws Exception {
//        // Arrange
//        Order order = new Order();
//        order.setId(2L);
//        order.setStatus(OrderStatus.COMPLETED);
//        order.setCustomerId(1010L);
//        List<Order> orders = new ArrayList<>();
//        orders.add(order);
//        when(orderService.getAllOrders()).thenReturn(orders);
//        // Act & Assert
//        mockMvc.perform(get("/order"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(2))
//                .andExpect(jsonPath("$[0].status").value("COMPLETED"))
//                .andExpect(jsonPath("$[0].customerId").value(1010L));
//    }

    @Test
    void getAllProductsReturnProducts() throws Exception {
        //Assert
        GenericProductResponseDto dto = new GenericProductResponseDto();
        dto.setId("d63a8cc6-8cd2-4e5f-9500-b55b00d65903");
        dto.setTitle("Best Phone");
        dto.setCategory("Electronics");
        dto.setPrice(200);
        Rating rating = new Rating();
        rating.setRate(20.0);
        rating.setCount(5);
        dto.setRating(rating);

        GenericProductResponseDto dto1 = new GenericProductResponseDto();
        dto1.setId("bd45a3d5-cf34-472a-b029-fab9c1b14c89");
        dto1.setTitle("Dabba Phone");
        dto1.setCategory("Toys");
        dto1.setPrice(200);
        dto1.setRating(rating);

        List<GenericProductResponseDto> dtos = new ArrayList<>();
        dtos.add(dto);
        dtos.add(dto1);

        when(productService.getAllProducts()).thenReturn(dtos);
        when(userServiceClient.validate(any())).thenReturn(SessionStatus.ACTIVE.name());

        mockMvc.perform(get("/products")
                        .header("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsInJvbGVzIjpbIlVTRVIiXX0.test"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(dtos)));
//                .andExpect(content().string("[{\"id\":\"d63a8cc6-8cd2-4e5f-9500-b55b00d65903\",\"title\":\"Best Phone\",\"price\":200,\"category\":\"Electronics\",\"description\":null,\"image\":null,\"rating\":{\"rate\":20.0,\"count\":5}},{\"id\":\"bd45a3d5-cf34-472a-b029-fab9c1b14c89\",\"title\":\"Dabba Phone\",\"price\":200,\"category\":\"Toys\",\"description\":null,\"image\":null,\"rating\":{\"rate\":20.0,\"count\":5}}]"));
    }

    @Test
    void CreateProductsSuccess() throws Exception {
        //Assert
        GenericProductRequestDto dto = new GenericProductRequestDto();
        dto.setId("d63a8cc6-8cd2-4e5f-9500-b55b00d65903");
        dto.setTitle("Best Phone");
        Category category = new Category();
        category.setName("Electronics");
        dto.setCategory(category);
        Price price = new Price();
        price.setValue(100.0);
        price.setCurrency("INR");
        dto.setPrice(price);
        Rating rating = new Rating();
        rating.setRate(20.0);
        rating.setCount(5);
        dto.setRating(rating);

        GenericDto resDto = new GenericDto();
        resDto.setId(dto.getId());
        resDto.setPrice(dto.getPrice().getValue());
        resDto.setTitle(dto.getTitle());
        resDto.setCategory(dto.getCategory().getName());

        String requestJson = convertToJson(dto);
        String responseJson = convertToJson(resDto);

//        when(productService.createProduct(eq(dto)).thenReturn(resDto); is better practice
        when(productService.createProduct(any(GenericProductRequestDto.class))).thenReturn(resDto);
        mockMvc.perform(post("/products")
                    .contentType(MediaType.APPLICATION_JSON)
//                        .header("token", new Object())
                    .content(requestJson)
                )
                .andExpect(status().is(200))
                .andExpect(content().string(responseJson));
    }

    @Test
    void deleteProductByIdSuccess() throws Exception {
        GenericProductResponseDto dto = new GenericProductResponseDto();
        dto.setId("d63a8cc6-8cd2-4e5f-9500-b55b00d65903");
        dto.setTitle("Test Product");
        when(productService.deleteProductById("d63a8cc6-8cd2-4e5f-9500-b55b00d65903")).thenReturn(dto);
        mockMvc.perform(delete("/products/d63a8cc6-8cd2-4e5f-9500-b55b00d65903"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(dto)));
    }

    private String convertToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}