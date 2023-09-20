package com.project.inventoryservice.service;

import com.project.inventoryservice.client.OrderClient;
import com.project.inventoryservice.client.ProductClient;
import com.project.inventoryservice.controller.request.AddInventoryRequest;
import com.project.inventoryservice.model.Inventory;
import com.project.inventoryservice.model.Order;
import com.project.inventoryservice.model.TransactionType;
import com.project.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;
    private final ProductClient productMicroserviceClient;
    private final OrderClient orderMicroserviceClient;

    @Autowired
    public InventoryService(InventoryRepository repository, ProductClient productMicroserviceClient, OrderClient orderMicroserviceClient) {
        this.repository = repository;
        this.productMicroserviceClient = productMicroserviceClient;
        this.orderMicroserviceClient = orderMicroserviceClient;
    }

    public int getInfoStock(Long productId) {
        var productInventoryList = findByProductId(productId);
        var totalIn = productInventoryList.stream().filter(productInventory -> productInventory.getTransactionType().equals(TransactionType.IN))
                .mapToDouble(Inventory::getQuantity)
                .sum();
        var totalOut = productInventoryList.stream().filter(productInventory -> productInventory.getTransactionType().equals(TransactionType.OUT))
                .mapToDouble(Inventory::getQuantity)
                .sum();

        return (int) (totalIn - totalOut);
    }

    public Inventory addInventoryEntry(AddInventoryRequest request) {
        var product = productMicroserviceClient.findById(request.productId());

        Order order = null;
        if (request.orderId() != null) { // orderId only required for transaction type OUT
            order = orderMicroserviceClient.findById(request.orderId());
        }

        var inventory = new Inventory(product, order, request.transactionType(), request.quantity());
        return repository.addInventoryEntry(inventory);
    }

    public Inventory findById(Long id) {
        return repository.findById(id);
    }

    public List<Inventory> findByProductId(Long productId) {
        return repository.findByProductId(productId);
    }

    public List<Inventory> findAll() {
        return repository.findAll();
    }
}
