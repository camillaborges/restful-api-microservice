package com.project.orderservice.config;

import com.project.orderservice.client.InventoryClient;
import com.project.orderservice.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    public static final String PRODUCT_MICRO_SERVICE_URL = "http://product-service";
    public static final String INVENTORY_MICRO_SERVICE_URL = "http://inventory-service";

    private final LoadBalancedExchangeFilterFunction filterFunction;

    @Autowired
    public WebClientConfig(LoadBalancedExchangeFilterFunction filterFunction) {
        this.filterFunction = filterFunction;
    }

    @Bean
    public WebClient productWebClient() {
        return WebClient.builder()
                .baseUrl(PRODUCT_MICRO_SERVICE_URL)
                // specify a filter on how to handle the load balance aspect.
                .filter(filterFunction)
                .build();
    }

    @Bean
    public ProductClient productClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(productWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(ProductClient.class);
    }

    @Bean
    public WebClient inventoryWebClient() {
        return WebClient.builder()
                .baseUrl(INVENTORY_MICRO_SERVICE_URL)
                // specify a filter on how to handle the load balance aspect.
                .filter(filterFunction)
                .build();
    }

    @Bean
    public InventoryClient inventoryClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(inventoryWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }
}
