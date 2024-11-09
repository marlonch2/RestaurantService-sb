package dev.marlon.restaurant_sb.models.forms;

import dev.marlon.restaurant_sb.models.entities.Item;
import dev.marlon.restaurant_sb.models.entities.Order;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemForm {

    @NotBlank
    private String name;

    @Min(0)
    private double price;

    @NotEmpty
    private List<Order> orders;

    public Item toEntity() {
        return Item.builder()
                .name(this.name)
                .price(this.price)
                .orders(this.orders)
                .build();
    }
}
