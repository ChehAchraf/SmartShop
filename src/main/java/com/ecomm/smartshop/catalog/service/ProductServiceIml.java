package com.ecomm.smartshop.catalog.service;

import com.ecomm.smartshop.catalog.dto.ProductRequest;
import com.ecomm.smartshop.catalog.dto.ProductResponse;
import com.ecomm.smartshop.catalog.entity.Product;
import com.ecomm.smartshop.catalog.mapper.ProductMapper;
import com.ecomm.smartshop.catalog.repository.ProductRepository;
import com.ecomm.smartshop.catalog.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceIml implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;


    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = mapper.toEntity(request);
        Product savedProduct = repository.save(product);
        return mapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product extistingProduct = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("no product"));
        mapper.updateEntityFromRequest(request,extistingProduct);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return repository.findById(id).map(mapper::toResponse)
                .orElseThrow(()->new RuntimeException("no product was found"));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return repository.findAll().stream().map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
