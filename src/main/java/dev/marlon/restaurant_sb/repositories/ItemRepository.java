package dev.marlon.restaurant_sb.repositories;

import dev.marlon.restaurant_sb.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
