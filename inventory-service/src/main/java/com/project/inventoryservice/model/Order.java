package com.project.inventoryservice.model;

import java.util.Date;

public class Order {
    private Long id;
    private Product product;
    private Long groupId;
    private Long quantity;
    private Date date;
    private State state;

    public Order() {
    }

    public Order(Long id, Product product, Long groupId, Long quantity, Date date, State state) {
        this.id = id;
        this.product = product;
        this.groupId = groupId;
        this.quantity = quantity;
        this.date = date;
        this.state = state;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId=" + product +
                ", groupId=" + groupId +
                ", quantity=" + quantity +
                ", date=" + date +
                ", state=" + state +
                '}';
    }
}
