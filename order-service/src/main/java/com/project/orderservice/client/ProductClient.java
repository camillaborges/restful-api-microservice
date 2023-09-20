package com.project.orderservice.client;

import com.project.orderservice.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

// The ProductClient it is just a "contract", the concrete implementation can be found at
// com.project.orderservice.config.WebClientConfig.productClient
@HttpExchange
public interface ProductClient {

    @GetExchange("/product/{id}")
    Product findById(@PathVariable Long id);

}
