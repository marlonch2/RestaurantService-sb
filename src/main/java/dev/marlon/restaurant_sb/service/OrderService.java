package dev.marlon.restaurant_sb.service;

import dev.marlon.restaurant_sb.exceptions.ResourceNotFoundException;
import dev.marlon.restaurant_sb.models.dtos.OrderDTO;
import dev.marlon.restaurant_sb.models.entities.Item;
import dev.marlon.restaurant_sb.models.entities.Order;
import dev.marlon.restaurant_sb.models.forms.OrderForm;
import dev.marlon.restaurant_sb.repositories.ItemRepository;
import dev.marlon.restaurant_sb.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderDTO createOrder(OrderForm orderForm) {
        Order order = new Order();
        order.setDescription(orderForm.getDescription());
        order.setTotalprice(orderForm.getTotalprice());

        Order savedOrder = orderRepository.save(order);
        return new OrderDTO(savedOrder.getId(), savedOrder.getDescription(), savedOrder.getTotalprice(), savedOrder.getItems());
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com o ID: " + id));
        return new OrderDTO(order.getId(), order.getDescription(), order.getTotalprice(), order.getItems());
    }

    public OrderDTO updateOrderById(Long id, OrderForm orderForm) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com o ID: " + id));

        order.setDescription(orderForm.getDescription());
        order.setTotalprice(orderForm.getTotalprice());

        Order updatedOrder = orderRepository.save(order);
        return new OrderDTO(updatedOrder.getId(), updatedOrder.getDescription(), updatedOrder.getTotalprice(), updatedOrder.getItems());
    }

    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com o ID: " + id));

        orderRepository.delete(order);
    }

    public Order addItemToOrder(Long orderId, Long itemId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if (orderOptional.isPresent() && itemOptional.isPresent()) {
            Order order = orderOptional.get();
            Item item = itemOptional.get();

            order.getItems().add(item);
            return orderRepository.save(order);
        }

        throw new ResourceNotFoundException("Item ou pedido não encontrado");
    }

    public List<Item> getItemsByOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            return List.copyOf(orderOptional.get().getItems());
        }
        throw new RuntimeException("Pedido não encontrado");
    }

}
