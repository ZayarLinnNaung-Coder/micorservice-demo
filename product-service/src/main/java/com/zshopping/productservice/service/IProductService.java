package com.zshopping.productservice.service;

import com.zshopping.productservice.dto.ProductRequest;
import com.zshopping.productservice.dto.ProductResponse;

import java.util.List;

public interface IProductService {

    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
