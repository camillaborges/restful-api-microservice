package com.project.orderservice.client.request;


import com.project.orderservice.model.TransactionType;

public record AddInventoryRequest(Long productId, Long orderId, TransactionType transactionType, int quantity) {

}
