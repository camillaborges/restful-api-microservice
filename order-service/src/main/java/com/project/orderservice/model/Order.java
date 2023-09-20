package com.project.orderservice.model;

import java.util.Date;

public class Order {
    private Long id;
    private Product product;
    private int quantity;
    private Date date;
    private State state;

    // Empty constructor needed to deserialize from Object value
    public Order() {

    }

    public Order(Product product, int quantity, State state) {
        this.product = product;
        this.quantity = quantity;
        this.state = state;
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
                ", quantity=" + quantity +
                ", date=" + date +
                ", state=" + state +
                '}';
    }
}
