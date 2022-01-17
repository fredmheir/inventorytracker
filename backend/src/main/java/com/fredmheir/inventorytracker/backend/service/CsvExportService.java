package com.fredmheir.inventorytracker.backend.service;

import com.fredmheir.inventorytracker.backend.model.Item;
import com.fredmheir.inventorytracker.backend.repository.ItemRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class CsvExportService {
    private static final Logger log = getLogger(String.valueOf(CsvExportService.class));

    private final ItemRepository itemRepository;

    public CsvExportService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void writeItemToCsv(Writer writer) {

        List<Item> items = itemRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (Item item : items) {
                csvPrinter.printRecord(item.getId(), item.getName(), item.getQty(), item.getCost());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }
}
