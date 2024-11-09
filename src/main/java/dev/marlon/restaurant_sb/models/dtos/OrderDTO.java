package dev.marlon.restaurant_sb.models.dtos;

import dev.marlon.restaurant_sb.models.entities.Item;
import dev.marlon.restaurant_sb.models.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private String description;
    
    private double totalprice;

    private List<Item> items;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.description = order.getDescription();
        this.totalprice = order.getTotalprice();
        this.items = order.getItems();
    }

    public static OrderDTO toDto(Order order) {
        return order == null ? null : new OrderDTO(order);
    }

    public static List<OrderDTO> toDto(List<Order> orders){
        return orders == null ? null : orders.stream().map(OrderDTO::new).toList();
    }
}
