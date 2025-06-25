package com.productservice.ProductService.services.thirdPartyClients.fakeStoreClientAdapter;

import com.productservice.ProductService.models.datamodels.Category;
import com.productservice.ProductService.services.interfaces.ICategoryService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component("categoryFakeStore")
public class FakeStoreCategory implements ICategoryService {
    private final RestTemplateBuilder restTemplateBuilder;
    public FakeStoreCategory(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Category> getCategories() {
        String url = "https://fakestoreapi.com/products/categories";
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(url, String[].class);
        List<Category> fakeStoreCategories = new ArrayList<>();
        if (responseEntity.getBody() != null){
            for (String categoryName : responseEntity.getBody()) {
                Category category = new Category();
                category.setName(categoryName);
                category.setId(UUID.randomUUID());
                fakeStoreCategories.add(category);
            }
        }
        return fakeStoreCategories;
    }

    @Override
    public void createCategory(Category category) {

    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return null;
    }
}
