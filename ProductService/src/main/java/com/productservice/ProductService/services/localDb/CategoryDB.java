package com.productservice.ProductService.services.localDb;

import com.productservice.ProductService.models.FakeStore.FakeStoreCategory;
import com.productservice.ProductService.services.interfaces.ICategoryService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("categoryLocalDb")
public class CategoryDB implements ICategoryService {
    @Override
    public List<FakeStoreCategory> getCategories() {
        return List.of();
    }
}
