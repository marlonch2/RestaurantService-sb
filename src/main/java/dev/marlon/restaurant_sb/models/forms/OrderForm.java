package dev.marlon.restaurant_sb.models.forms;

import dev.marlon.restaurant_sb.models.entities.Item;
import dev.marlon.restaurant_sb.models.entities.Order;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {

    @NotEmpty(message = "cannot be empty")
    private String description;

    @Min(0)
    private double totalprice;

    @NotEmpty
    private List<Item> items;

    public Order toEntity() {
        return Order.builder()
                .description(this.description)
                .items(this.items)
                .build();
    }
}
