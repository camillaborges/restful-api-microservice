package com.project.productservice.controller;

import com.project.productservice.controller.request.AddProductRequest;
import com.project.productservice.controller.request.UpdateProductRequest;
import com.project.productservice.model.Product;
import com.project.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product addProduct(@RequestBody AddProductRequest product) {
        return service.addProduct(product);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping
    public List<Product> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        return service.updateProduct(id, request);
    }


}
