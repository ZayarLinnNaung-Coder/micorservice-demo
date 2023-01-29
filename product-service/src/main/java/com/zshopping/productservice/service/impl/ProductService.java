package com.zshopping.productservice.service.impl;

import com.zshopping.productservice.converter.ProductRequestConverter;
import com.zshopping.productservice.converter.ProductResponseConverter;
import com.zshopping.productservice.dto.ProductRequest;
import com.zshopping.productservice.dto.ProductResponse;
import com.zshopping.productservice.model.Product;
import com.zshopping.productservice.repository.ProductRepository;
import com.zshopping.productservice.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = ProductRequestConverter.convertDtoToEntity(productRequest);
        productRepository.save(product);

        logger.info("Product {} is successfully saved", product.getId());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponseConverter::convertEntityToDto).toList();
    }

}
