package com.project.orderservice.controller.request;

import com.project.orderservice.model.State;

import java.util.Date;

public record AddOrderRequest(Long productId, int quantity, Date date, State state) {
}
