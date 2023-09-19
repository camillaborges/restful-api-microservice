package com.project.inventoryservice.model;

import java.util.Date;

public class Inventory {
    private Long id;
    private Product product;
    private Order order;
    private TransactionType transactionType;
    private int quantity;
    private Date date;

    public Inventory(Long id, Product product, Order order, TransactionType transactionType, int quantity) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = new Date();
    }

    public Inventory(Product product, Order order, TransactionType transactionType, int quantity) {
        this.product = product;
        this.order = order;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", product=" + product +
                ", order=" + order +
                ", transactionType=" + transactionType +
                ", quantity=" + quantity +
                ", date=" + date +
                '}';
    }
}
