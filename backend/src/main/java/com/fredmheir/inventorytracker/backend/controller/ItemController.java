package com.fredmheir.inventorytracker.backend.controller;

import com.fredmheir.inventorytracker.backend.model.Item;
import com.fredmheir.inventorytracker.backend.service.CsvExportService;
import com.fredmheir.inventorytracker.backend.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/inventory")
public class ItemController {

	private final CsvExportService csvExportService;

	private final ItemService itemService;

	public ItemController(CsvExportService csvExportService, ItemService itemService) {
		this.csvExportService = csvExportService;
		this.itemService = itemService;
	}

	@RequestMapping(path = "/items/export")
	public void getAllItemsInCsv(HttpServletResponse servletResponse) throws IOException {
		servletResponse.setContentType("text/csv");
		servletResponse.addHeader("Content-Disposition", "attachment; filename=\"inventory.csv\"");
		csvExportService.writeItemToCsv(servletResponse.getWriter());
	}

	@GetMapping("/items")
	public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) String name) {
		try {
			List<Item> items = itemService.getAllItems(name);
			if (items.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>(items, HttpStatus.OK);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable("id") long id) {
		Optional<Item> itemData = itemService.getItemById(id);

		if (itemData.isPresent()) {
			return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/items")
	public ResponseEntity<Item> createItem(@RequestBody Item item) {
		try {
			Item _item = itemService.createItem(item);
			return new ResponseEntity<>(_item, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Item item) {
		try {
			Item _item = itemService.updateItem(id, item);
			return new ResponseEntity<>(_item, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/items/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {
		try {
			itemService.deleteItem(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/items")
	public ResponseEntity<HttpStatus> deleteAllItems() {
		try {
			itemService.deleteAllItems();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
