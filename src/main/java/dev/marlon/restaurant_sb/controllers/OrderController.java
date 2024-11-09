package dev.marlon.restaurant_sb.controllers;

import dev.marlon.restaurant_sb.models.dtos.OrderDTO;
import dev.marlon.restaurant_sb.models.entities.Item;
import dev.marlon.restaurant_sb.models.entities.Order;
import dev.marlon.restaurant_sb.models.forms.OrderForm;
import dev.marlon.restaurant_sb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderForm orderForm) {
        OrderDTO createdOrder = orderService.createOrder(orderForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<Order> addItemToOrder(@PathVariable Long orderId, @PathVariable Long itemId) {
        Order order = orderService.addItemToOrder(orderId, itemId);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderForm orderForm) {
        OrderDTO updatedOrder = orderService.updateOrderById(id, orderForm);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<Item>> getItemsByOrder(@PathVariable Long orderId) {
        List<Item> items = orderService.getItemsByOrder(orderId);
        return ResponseEntity.ok(items);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }
}
