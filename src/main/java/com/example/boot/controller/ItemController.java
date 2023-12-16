package com.example.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.entity.Item;
import com.example.boot.repository.ItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class ItemController {

	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/api/items")
	public List<Item> getItems() {
		
		List<Item> items = itemRepository.findAll();
		
		return items;

	}

//	@GetMapping("/api/items")
//	public List<String> getItems(){
//		List<String> items = new ArrayList<>();
//		items.add("alpha");
//		items.add("beta");
//		items.add("gamma");
//		
//		return items;
//	}
}
