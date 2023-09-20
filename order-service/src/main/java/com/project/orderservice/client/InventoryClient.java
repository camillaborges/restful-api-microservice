package com.project.orderservice.client;

import com.project.orderservice.client.request.AddInventoryRequest;
import com.project.orderservice.model.Inventory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

// The InventoryClient it is just a "contract", the concrete implementation can be found at
// com.project.orderservice.config.WebClientConfig.inventoryClient
@HttpExchange
public interface InventoryClient {

    @GetExchange("/inventory/product/{productId}/stock")
    int getInfoStock(@PathVariable Long productId);

    @PostExchange("/inventory")
    Inventory addInventory(@RequestBody AddInventoryRequest inventoryRequest);

}
