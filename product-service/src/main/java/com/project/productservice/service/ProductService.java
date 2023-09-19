package com.project.productservice.service;

import com.project.productservice.controller.request.AddProductRequest;
import com.project.productservice.controller.request.UpdateProductRequest;
import com.project.productservice.model.Product;
import com.project.productservice.repository.ProductRepository;
import com.project.productservice.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final TypeRepository typeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, TypeRepository typeRepository) {
        this.productRepository = productRepository;
        this.typeRepository = typeRepository;
    }

    public Product addProduct(AddProductRequest request) {
        var productType = typeRepository.findById(request.typeId());
        var newProduct = new Product(request.name(), request.description(), productType);
        return productRepository.addProduct(newProduct);
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, UpdateProductRequest request) {
        return productRepository.updateProduct(id, request);
    }
}
