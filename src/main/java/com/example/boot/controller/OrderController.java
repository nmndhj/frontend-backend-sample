package com.example.boot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.boot.dto.OrderDto;
import com.example.boot.entity.Cart;
import com.example.boot.entity.Item;
import com.example.boot.entity.Member;
import com.example.boot.entity.Order;
import com.example.boot.repository.CartRepository;
import com.example.boot.repository.ItemRepository;
import com.example.boot.repository.OrderRepository;
import com.example.boot.service.JwtService;

@RestController
public class OrderController {

	@Autowired
	JwtService jwtService;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CartRepository cartRepository;
	
	@GetMapping("/api/orders")
	public ResponseEntity getOrder(
			@CookieValue(value = "token", required = false) String token) {
		
		if ( !jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		int memberId = jwtService.getId(token);
		List<Order> orders = orderRepository.findByMemberIdOrderByIdDesc(memberId);
		
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	@Transactional
	@PostMapping("/api/orders")
	public ResponseEntity pushOrder(
			@RequestBody OrderDto dto,
			@CookieValue(value = "token", required = false) String token){
		
		if ( !jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		int memberId = jwtService.getId(token);
		Order newOrder = new Order();
		
		newOrder.setMemberId(jwtService.getId(token));
		newOrder.setName(dto.getName());
		newOrder.setAddress(dto.getAddress());
		newOrder.setPayment(dto.getPayment());
		newOrder.setCardNumber(dto.getCardNumber());
		newOrder.setItems(dto.getItems());
		
		orderRepository.save(newOrder);
		cartRepository.deleteByMemberId(memberId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}	
	
}
