package com.project.inventoryservice.controller.request;

import com.project.inventoryservice.model.TransactionType;

public record AddInventoryRequest(Long productId, Long orderId, TransactionType transactionType, int quantity) {

}
