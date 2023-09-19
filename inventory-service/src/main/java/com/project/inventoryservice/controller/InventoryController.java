package com.project.inventoryservice.controller;

import com.project.inventoryservice.controller.request.AddInventoryRequest;
import com.project.inventoryservice.model.Inventory;
import com.project.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    @Autowired
    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping
    public Inventory addInventory(@RequestBody AddInventoryRequest inventoryRequest) {
        return service.addInventoryEntry(inventoryRequest);
    }

    @GetMapping("/{id}")
    public Inventory findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/product/{productId}")
    public List<Inventory> findByProductId(@PathVariable Long productId) {
        return service.findByProductId(productId);
    }

    @GetMapping
    public List<Inventory> findAll() {
        return service.findAll();
    }

    @GetMapping("/product/{productId}/stock")
    public int getInfoStock(@PathVariable Long productId) {
        return service.getInfoStock(productId);
    }
}
