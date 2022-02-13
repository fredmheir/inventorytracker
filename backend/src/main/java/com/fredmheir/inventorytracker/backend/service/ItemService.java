package com.fredmheir.inventorytracker.backend.service;

import com.fredmheir.inventorytracker.backend.model.Item;
import com.fredmheir.inventorytracker.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	public List<Item> getAllItems(@RequestParam(required = false) String name) {
		List<Item> items = new ArrayList<>();
		if (name == null)
			items.addAll(itemRepository.findAll());
		else
			items.addAll(itemRepository.findByName(name));
		return items;
	}

	public Optional<Item> getItemById(long id) {
		return itemRepository.findById(id);
	}

	public Item createItem(Item item) {
		return itemRepository.save(new Item(item.getName(), item.getQty(), item.getCost()));
	}

	public Item updateItem(long id, Item item) throws Exception {
		Optional<Item> itemData = itemRepository.findById(id);
		if (itemData.isPresent()) {
			Item _item = itemData.get();
			_item.setName(item.getName());
			_item.setQty(item.getQty());
			_item.setCost(item.getCost());
			return itemRepository.save(_item);
		}
		else {
			throw new Exception("Item not present.");
		}
	}

	public void deleteItem(long id) {
		itemRepository.deleteById(id);
	}

	public void deleteAllItems() {
		itemRepository.deleteAll();
	}

}
