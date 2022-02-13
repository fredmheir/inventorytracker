package com.fredmheir.inventorytracker.backend.controller;

import com.fredmheir.inventorytracker.backend.model.Item;
import com.fredmheir.inventorytracker.backend.service.CsvExportService;
import com.fredmheir.inventorytracker.backend.service.ItemService;
import com.fredmheir.inventorytracker.backend.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ItemService itemService;

	@MockBean
	private CsvExportService csvExportService;

	@Test
	public void getAllItemsTest() throws Exception {
		// Arrange
		Item item1 = new Item("Item 1", 10, (float) 5.25);
		Item item2 = new Item("Item 2", 3, (float) 1.00);
		Item item3 = new Item("Item 3", 17, (float) 20.12);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		given(itemService.getAllItems(null)).willReturn(items);

		// Act & Assert
		mvc.perform(get("/inventory/items").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].name", is("Item 1")))
				.andExpect(jsonPath("$[1].name", is("Item 2"))).andExpect(jsonPath("$[2].name", is("Item 3")));

	}

	@Test
	public void getAllItemsWithNameTest() throws Exception {
		// Arrange
		Item item1 = new Item("Item 1", 10, (float) 5.25);
		Item item2 = new Item("Item 2", 3, (float) 1.00);
		Item item3 = new Item("Item 2", 17, (float) 1.15);
		List<Item> items = new ArrayList<>();
		items.add(item2);
		items.add(item3);
		given(itemService.getAllItems("Item 2")).willReturn(items);

		// Act & Assert
		mvc.perform(get("/inventory/items").contentType(MediaType.APPLICATION_JSON).param("name", "Item 2"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is("Item 2")));
	}

	@Test
	public void getItemByIdTest() throws Exception {
		// Arrange
		Item item1 = new Item(1, "Item 1", 10, (float) 5.25);
		Item item2 = new Item(2, "Item 2", 3, (float) 1.00);
		Item item3 = new Item(3, "Item 3", 17, (float) 20.12);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		Optional<Item> optionalItem2 = Optional.of(item2);
		given(itemService.getItemById(2)).willReturn(optionalItem2);

		// Act & Assert
		mvc.perform(get("/inventory/items/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", aMapWithSize(4))).andExpect(jsonPath("$.id", is(2)));

	}

	@Test
	public void createItemTest() throws Exception {
		// Arrange
		Item item = new Item(23, "Item 23", 5, (float) 28.34);
		given(itemService.createItem(item)).willReturn(item);

		// Act & Assert
		mvc.perform(post("/inventory/items").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(item)))
				.andExpect(status().isCreated());
	}

	@Test
	public void updateItemTest() throws Exception {
		// Arrange
		Item item = new Item(23, "Item 23", 5, (float) 28.34);

		// Act & Assert
		mvc.perform(put("/inventory/items/23").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(item)))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteItemTest() throws Exception {
		// Arrange
		Item item = new Item(23, "Item 23", 5, (float) 28.34);
		doNothing().when(itemService).deleteItem(item.getId());

		// Act & Assert
		mvc.perform(delete("/inventory/items/23").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

	}

	@Test
	public void deleteAllItemsTest() throws Exception {
		// Arrange
		Item item = new Item(23, "Item 1", 8, (float) 0.75);
		doNothing().when(itemService).deleteItem(item.getId());

		// Act & Assert
		mvc.perform(delete("/inventory/items/23").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

	}

	@Test
	public void getAllItemsInCsvTest() throws Exception {
		// Assert
		HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);

		// Act
		RequestBuilder requestBuilders = MockMvcRequestBuilders.get("/inventory/items/export")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Content-Disposition", "attachment; filename=\"inventory.csv\"")
				.accept(MediaType.APPLICATION_JSON);

		// Assert
		mvc.perform(requestBuilders).andDo(print()).andExpect(status().isOk()).andReturn();

	}

}
