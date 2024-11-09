package dev.marlon.restaurant_sb.repositories;

import dev.marlon.restaurant_sb.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
