package dev.marlon.restaurant_sb.models.dtos;

import dev.marlon.restaurant_sb.models.entities.Item;
import dev.marlon.restaurant_sb.models.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;

    private String name;

    private double price;

    private List<Order> orders;

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.orders = item.getOrders();
    }

    public static ItemDTO toDto(Item item) {
        return item == null ? null : new ItemDTO(item);
    }

    public static List<ItemDTO> toDto(List<Item> items){
        return items == null ? null : items.stream().map(ItemDTO::new).toList();
    }
}
