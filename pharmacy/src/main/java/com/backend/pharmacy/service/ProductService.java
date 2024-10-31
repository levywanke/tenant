package com.backend.pharmacy.service;

import org.springframework.stereotype.Service;

import com.backend.pharmacy.domain.Product;
import com.backend.pharmacy.repository.*;
import com.backend.pharmacy.tenant.TenantIdentifierResolver;

import java.util.List;

@Service
public class ProductService {
  
    private final ProductRepository productRepository;
    private final TenantIdentifierResolver tenantIdentifierResolver; 

    public ProductService(ProductRepository productRepository, TenantIdentifierResolver tenantIdentifierResolver) {
        this.productRepository = productRepository;
        this.tenantIdentifierResolver = tenantIdentifierResolver; 
    }

    public List<Product> getAllProducts() {
        String tenantId = tenantIdentifierResolver.resolveTenant(); 
        //System.out.println("Fetching products for Tenant ID: " + tenantId);  
        return productRepository.findByTenantId(tenantId); 
    }

    public Product createProduct(Product product) {
        String tenantId = tenantIdentifierResolver.resolveTenant(); 
        product.setTenantId(tenantId); 
        return productRepository.save(product);
    }
}
