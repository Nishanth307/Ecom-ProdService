package com.productservice.ProductService.services.interfaces;

import com.productservice.ProductService.models.FakeStore.FakeStoreCategory;

import java.util.List;

public interface ICategoryService {
    List<FakeStoreCategory> getCategories();
}
