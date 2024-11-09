package dev.marlon.restaurant_sb.service;

import dev.marlon.restaurant_sb.exceptions.ResourceNotFoundException;
import dev.marlon.restaurant_sb.models.dtos.ItemDTO;
import dev.marlon.restaurant_sb.models.entities.Item;
import dev.marlon.restaurant_sb.models.forms.ItemForm;
import dev.marlon.restaurant_sb.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public ItemDTO createItem(ItemForm itemForm) {
        Item item = new Item();
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());

        Item savedItem = itemRepository.save(item);
        return new ItemDTO(savedItem.getId(), savedItem.getName(), savedItem.getPrice(), savedItem.getOrders());
    }

    public ItemDTO getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado com o ID: " + id));
        return new ItemDTO(item.getId(), item.getName(), item.getPrice(),item.getOrders());
    }

    public ItemDTO updateItemById(Long id, ItemForm itemForm) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado com o ID: " + id));

        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());

        Item updatedItem = itemRepository.save(item);
        return new ItemDTO(updatedItem.getId(), updatedItem.getName(), updatedItem.getPrice(), updatedItem.getOrders());
    }

    public void deleteItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado com o ID: " + id));

        itemRepository.delete(item);
    }

}
