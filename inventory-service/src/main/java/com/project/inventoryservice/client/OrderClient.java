package com.project.inventoryservice.client;

import com.project.inventoryservice.model.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

// The OrderClient it is just a "contract", the concrete implementation can be found at
// com.project.inventoryservice.config.WebClientConfig.orderClient
@HttpExchange
public interface OrderClient {

    @GetExchange("/order/{id}")
    Order findById(@PathVariable Long id);

}
