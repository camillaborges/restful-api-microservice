package com.project.orderservice.repository;

import com.project.orderservice.exception.EntityNotFoundException;
import com.project.orderservice.model.Order;
import com.project.orderservice.model.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository extends AutoIncrementId {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    private final List<Order> orders = new ArrayList<>();

    public Order addOrder(Order order) {
        logger.info("Add new Order: {}", order);
        // Set next sequential ID
        order.setId(getNextAutoIncrementId());
        orders.add(order);
        return order;
    }

    public Order findById(Long id) {
        logger.info("Find Order by ID: {}", id);
        return orders.stream().filter(order -> order.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Order was not found."));
    }

    public void deleteById(Long id) {
        logger.info("Delete Order by ID: {}", id);
        orders.removeIf(order -> order.getId().equals(id));
    }

    public Order updateState(Long id, State state) {
        logger.info("Update Order state: {}, by ID: {}", state, id);
        Optional<Order> optionalOrder = orders.stream().filter(order -> order.getId().equals(id)).findFirst();

        if (optionalOrder.isPresent()) {
            var order = optionalOrder.get();
            order.setState(state);
            return order;
        }

        logger.warn("Not able to get Order with ID: {}", id);
        throw new EntityNotFoundException("Order was not found");
    }

    public List<Order> findAll() {
        logger.info("Returning all orders: {} in total", orders.size());
        return orders;
    }
}
