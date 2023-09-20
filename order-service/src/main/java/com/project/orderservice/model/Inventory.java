package com.project.orderservice.model;

import java.util.Date;

public record Inventory(Long id, Product product, Order order, TransactionType transactionType, int quantity,
                        Date date) {
}
