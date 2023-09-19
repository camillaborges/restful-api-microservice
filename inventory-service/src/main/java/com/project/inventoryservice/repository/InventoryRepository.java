package com.project.inventoryservice.repository;

import com.project.inventoryservice.model.Inventory;
import com.project.inventoryservice.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InventoryRepository extends AutoIncrementId {

    private static final Logger logger = LoggerFactory.getLogger(InventoryRepository.class);

    private final List<Inventory> inventoryList = new ArrayList<>();

    public Inventory addInventoryEntry(Inventory inventory) {
        logger.info("Add new item to Inventory: {}", inventory);
        // Set next sequential ID
        inventory.setId(getNextAutoIncrementId());
        inventoryList.add(inventory);
        return inventory;
    }

    public Inventory findById(Long id) {
        logger.info("Find Inventory by ID: {}", id);
        return inventoryList.stream().filter(inventory -> inventory.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Inventory was not found."));
    }

    public List<Inventory> findByProductId(Long productId) {
        logger.info("Find product in the inventory by ID: {}", productId);
        return inventoryList.stream().filter(inventory -> inventory.getProduct().id().equals(productId))
                .toList();
    }

    public List<Inventory> findAll() {
        logger.info("Returning all items from the inventory: {} ", inventoryList.size());
        return inventoryList;
    }
}
