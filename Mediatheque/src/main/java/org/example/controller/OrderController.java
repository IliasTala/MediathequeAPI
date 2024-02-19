package org.example.controller;


import org.example.model.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public List<Order> list() {
        return orderService.findAllOrders();
    }

    @PostMapping("/save")
    public Order save(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<Order> getById(@PathVariable(name = "id") Long orderId) {
        return orderService.getOrderByOrderNumber(orderId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}