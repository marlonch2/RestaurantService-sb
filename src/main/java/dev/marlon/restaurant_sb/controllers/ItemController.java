package dev.marlon.restaurant_sb.controllers;

import dev.marlon.restaurant_sb.models.dtos.ItemDTO;
import dev.marlon.restaurant_sb.models.entities.Item;
import dev.marlon.restaurant_sb.models.forms.ItemForm;
import dev.marlon.restaurant_sb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemForm itemForm) {
        ItemDTO createdItem = itemService.createItem(itemForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id, @RequestBody ItemForm itemForm) {
        ItemDTO updatedItem = itemService.updateItemById(id, itemForm);
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) {
        ItemDTO item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> orders = itemService.getAllItems();
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItemById(itemId);
        return ResponseEntity.noContent().build();
    }
}
