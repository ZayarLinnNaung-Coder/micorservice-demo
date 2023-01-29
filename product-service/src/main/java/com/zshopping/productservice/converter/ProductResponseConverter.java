package com.zshopping.productservice.converter;

import com.zshopping.productservice.dto.ProductResponse;
import com.zshopping.productservice.model.Product;

public class ProductResponseConverter {
    public static ProductResponse convertEntityToDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
    public static Product convertDtoToEntity(ProductResponse dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }
}
