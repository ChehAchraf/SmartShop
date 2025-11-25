package com.ecomm.smartshop.catalog.service.interfaces;

import com.ecomm.smartshop.catalog.dto.ProductRequest;
import com.ecomm.smartshop.catalog.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
    void deleteProduct(Long id);
}
