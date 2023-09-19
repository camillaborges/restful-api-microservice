package com.project.productservice.repository;

import com.project.productservice.controller.request.UpdateProductRequest;
import com.project.productservice.exception.EntityNotFoundException;
import com.project.productservice.model.Product;
import com.project.productservice.model.Type;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository extends AutoIncrementId {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private final Map<Long, Product> catalogProducts = new HashMap<>();

    private final TypeRepository typeRepository;

    @Autowired
    public ProductRepository(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @PostConstruct
    public void persistInitialProductData() {
        logger.info("Initializing dummy Product data...");
        catalogProducts.put(1L, new Product(1L, "Counter-Strike: Global Offensive", "Game", new Type(1L, "PC Game")));
        catalogProducts.put(2L, new Product(2L, "PlayStation 5", "Sony console", new Type(2L, "Sony")));
        catalogProducts.put(3L, new Product(3L, "XBox 360", "Microsoft console", new Type(3L, "Microsoft")));

        // inform the last hardcoded ID (3) to the AutoIncrementId class, to keep track.
        setCurrentAutoIncrementId(3L);
    }

    public Product addProduct(Product product) {
        logger.info("Add new Product to catalog: {}", product);
        // Set next sequential ID
        product.setId(getNextAutoIncrementId());
        catalogProducts.put(product.getId(), product);
        return product;
    }

    public Product findById(Long id) {
        logger.info("Find Product on catalog by ID: {}", id);
        return catalogProducts.entrySet().stream()
                .filter(productMap -> productMap.getKey().equals(id))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new EntityNotFoundException("Product was not found."));
    }

    public void deleteById(Long id) {
        logger.info("Delete product on catalog by ID: {}", id);
        catalogProducts.remove(id);
    }

    public List<Product> findAll() {
        logger.info("Returning all products: {} in total", catalogProducts.size());
        return catalogProducts.values().stream().toList();
    }

    public Product updateProduct(Long id, UpdateProductRequest request) {
        var targetProduct = findById(id);
        targetProduct.setName(request.name());
        targetProduct.setDescription(request.description());
        var type = typeRepository.findById(request.typeId());
        targetProduct.setType(type);
        return targetProduct;
    }
}
