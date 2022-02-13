package com.fredmheir.inventorytracker.backend.service;

import com.fredmheir.inventorytracker.backend.model.Item;
import com.fredmheir.inventorytracker.backend.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ItemService.class)
public class ItemServiceTest {

	@MockBean
	ItemRepository itemRepository;

	@InjectMocks
	ItemService itemService;

	@Test
	public void getAllItemsTest() {

		// Arrange
		List<Item> items = new ArrayList();
		items.add(new Item(1, "Item 1", 20, (float) 50.21));
		items.add(new Item(2, "Item 2", 5, (float) 10.23));
		items.add(new Item(3, "Item 3", 102, (float) 0.98));
		given(itemRepository.findAll()).willReturn(items);

		// Act
		List<Item> expected = itemService.getAllItems(null);

		// Assert
		assertEquals(expected, items);
		verify(itemRepository).findAll();
	}

	@Test
	public void getAllItemsWithNameTest() {
		// Arrange
		List<Item> items = new ArrayList();
		Item item1 = new Item(1, "Item 1", 20, (float) 50.21);
		Item item2_1 = new Item(2, "Item 2", 5, (float) 10.23);
		Item item2_2 = new Item(3, "Item 3", 102, (float) 0.98);

		items.add(item2_1);
		items.add(item2_2);
		given(itemRepository.findByName("Item 2")).willReturn(items);

		// Act
		List<Item> result = itemService.getAllItems("Item 2");

		// Assert
		assertEquals(result, items);
		verify(itemRepository).findByName("Item 2");
	}

	@Test
	public void getItemByIdTest() {
		// Arrange
		Item item = new Item(12, "Item 12", 20, (float) 50.21);
		given(itemRepository.findById((long) 12)).willReturn(Optional.of(item));

		// Act
		Optional<Item> result = itemService.getItemById(12);

		// Assert
		assertEquals(result, Optional.of(item));
		verify(itemRepository).findById((long) 12);
	}

	@Test
	public void createItemTest() {
		// Arrange
		Item item = new Item(12, "Item 12", 20, 50);
		when(itemRepository.save(ArgumentMatchers.any(Item.class))).thenReturn(item);

		// Act
		Item itemCreated = itemService.createItem(item);

		// Assert
		assertEquals(itemCreated.getId(), item.getId());
		assertEquals(itemCreated.getName(), item.getName());
		assertEquals(itemCreated.getQty(), item.getQty());
		assertEquals(itemCreated.getCost(), item.getCost());
	}

	@Test
	public void updateItem() {
		// Arrange
		Item item = new Item(12, "Item 12", 20, 50);

		Item updatedItem = new Item(12, "Item 12", 22, 47);

		given(itemRepository.findById(item.getId())).willReturn(Optional.of(item));
		given(itemRepository.save(any(Item.class))).willReturn(updatedItem);

		// Act
		Item result = new Item();
		try {
			result = itemService.updateItem(12, updatedItem);
		}
		catch (Exception e) {
			fail();
		}

		// Assert
		assertEquals(result, updatedItem);
	}

	@Test
	public void updateItemNotPresent() throws Exception {
		// Arrange
		Item item = new Item(12, "Item 12", 20, 50);

		Item updatedItem = new Item(12, "Item 12", 22, 47);

		// Act

		Exception exception = assertThrows(Exception.class, () -> itemService.updateItem(13, updatedItem),
				"Item not present.");

		// Assert
		assertEquals(exception.getMessage(), "Item not present.");
	}

	@Test
	public void deleteItemTest() {
		// Arrange
		Item item = new Item(12, "Item 12", 20, (float) 50.21);
		doNothing().when(itemRepository).deleteById((long) 12);

		// Act
		itemService.deleteItem(12);

		// Assert
		verify(itemRepository).deleteById((long) 12);
	}

	@Test
	public void deleteAllItemTest() {
		// Arrange
		Item item = new Item(12, "Item 12", 20, (float) 50.21);
		doNothing().when(itemRepository).deleteAll();

		// Act
		itemService.deleteAllItems();

		// Assert
		verify(itemRepository).deleteAll();
	}

}
