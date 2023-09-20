package com.project.orderservice.service;

import com.project.orderservice.client.InventoryClient;
import com.project.orderservice.client.ProductClient;
import com.project.orderservice.client.request.AddInventoryRequest;
import com.project.orderservice.controller.request.AddOrderRequest;
import com.project.orderservice.exception.OutOfStockException;
import com.project.orderservice.model.Order;
import com.project.orderservice.model.State;
import com.project.orderservice.model.TransactionType;
import com.project.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository repository;
    private final ProductClient productMicroserviceClient;
    private final InventoryClient inventoryMicroserviceClient;

    @Autowired
    public OrderService(OrderRepository repository, ProductClient productMicroserviceClient, InventoryClient inventoryMicroserviceClient) {
        this.repository = repository;
        this.productMicroserviceClient = productMicroserviceClient;
        this.inventoryMicroserviceClient = inventoryMicroserviceClient;
    }

    public Order addOrder(AddOrderRequest request) {
        // Make sure the product exists in the product microservice
        var product = productMicroserviceClient.findById(request.productId());
        // Get the Product stock and check against the order's quantity.
        var quantityStock = inventoryMicroserviceClient.getInfoStock(request.productId());

        if (quantityStock >= request.quantity()) {
            var order = new Order(product, request.quantity(), request.state());
            return repository.addOrder(order);
        }

        logger.warn("Order cannot be done due to missing Stock for product ID: {}", request.productId());
        throw new OutOfStockException(String.format("There is no stock available for the Product ID: %s", request.productId()));
    }

    public Order findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order updateState(Long id, State state) {
        logger.info("Update Order ID: {} to state: {}", id, state);
        if (state.equals(State.DELIVERED)) {
            logger.info(" Order ID: {} with DELIVERED state, updating its stock on Inventory service", id);
            var originalOrder = findById(id);
            var stockRequest = new AddInventoryRequest(originalOrder.getProduct().id(), originalOrder.getId(), TransactionType.OUT, originalOrder.getQuantity());
            inventoryMicroserviceClient.addInventory(stockRequest);
        }
        return repository.updateState(id, state);
    }
}
