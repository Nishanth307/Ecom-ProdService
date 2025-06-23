package com.productservice.ProductService.services.thirdPartyClients.fakeStoreClient;

import com.productservice.ProductService.models.FakeStore.FakeStoreCategory;
import com.productservice.ProductService.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Component("categoryFakeStore")
public class FakeStoreCategoryAdapter  implements ICategoryService {
    private final RestTemplateBuilder restTemplateBuilder;
    public FakeStoreCategoryAdapter(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<FakeStoreCategory> getCategories() {
        String url = "https://fakestoreapi.com/products/categories";
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(url, String[].class);
        List<FakeStoreCategory> fakeStoreCategories = new ArrayList<>();
        if (responseEntity.getBody() != null){
            for (String categoryName : responseEntity.getBody()) {
                FakeStoreCategory category = new FakeStoreCategory();
                category.setName(categoryName);
                category.setId(UUID.randomUUID());
                fakeStoreCategories.add(category);
            }
        }
        return fakeStoreCategories;
    }
}
