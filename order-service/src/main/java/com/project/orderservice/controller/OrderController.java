package com.project.orderservice.controller;

import com.project.orderservice.controller.request.AddOrderRequest;
import com.project.orderservice.model.Order;
import com.project.orderservice.model.State;
import com.project.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order addOrder(@RequestBody AddOrderRequest orderRequest) {
        return service.addOrder(orderRequest);
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping
    public List<Order> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public Order updateState(@PathVariable Long id, @RequestParam State state) {
        return service.updateState(id, state);
    }
}
