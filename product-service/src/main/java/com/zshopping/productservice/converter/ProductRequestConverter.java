package com.zshopping.productservice.converter;

import com.zshopping.productservice.dto.ProductRequest;
import com.zshopping.productservice.model.Product;

public class ProductRequestConverter {
    public static ProductRequest convertEntityToDto(Product product) {
        return ProductRequest.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Product convertDtoToEntity(ProductRequest dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }
}
