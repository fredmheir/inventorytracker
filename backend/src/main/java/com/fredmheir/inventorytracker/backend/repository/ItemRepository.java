package com.fredmheir.inventorytracker.backend.repository;

import com.fredmheir.inventorytracker.backend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByName(String name);

    List<Item> findByCost(int cost);

    List<Item> findByQty(int qty);

}
