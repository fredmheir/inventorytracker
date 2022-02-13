package com.fredmheir.inventorytracker.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "qty")
	private int qty;

	@Column(name = "cost")
	private float cost;

	public Item(String name, int qty, float cost) {
		this.name = name;
		this.qty = qty;
		this.cost = cost;
	}

}
