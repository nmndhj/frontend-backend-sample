package com.example.boot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.boot.entity.Cart;
import com.example.boot.entity.Item;
import com.example.boot.repository.CartRepository;
import com.example.boot.repository.ItemRepository;
import com.example.boot.service.JwtService;

@RestController
public class CartController {
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	JwtService jwtService;
	
	@GetMapping("/api/cart/items")
	public ResponseEntity getCartItems(
			@CookieValue(value = "token", required = false)String token) {
		
		if ( !jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		int memeberId = jwtService.getId(token);
		List<Cart> carts = cartRepository.findByMemberId(memeberId);
		//List<Integer> itemIds = carts.stream().map(Cart::getItemId).toList();  ---java8 에서는 밑의 방식으로.
		List<Integer> itemIds = carts.stream().map(Cart::getItemId).collect(Collectors.toList());
		List<Item> items = itemRepository.findByIdIn(itemIds);
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@PostMapping("/api/cart/items/{itemId}")
	public ResponseEntity pushCartItem(
			@PathVariable("itemId") int itemId,
			@CookieValue(value = "token", required = false) String token){
		
		if ( !jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		int memeberId = jwtService.getId(token);
		Cart cart = cartRepository.findByMemberIdAndItemId(memeberId, itemId);
		
		if(cart == null) {
			Cart newCart = new Cart();
			newCart.setMemberId(memeberId);
			newCart.setItemId(itemId);
			cartRepository.save(newCart);
			
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/api/cart/items/{itemId}")
	public ResponseEntity removeCartItem(
			@PathVariable("itemId") int itemId,
			@CookieValue(value = "token", required = false) String token){
		
		if ( !jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		int memeberId = jwtService.getId(token);
		Cart cart = cartRepository.findByMemberIdAndItemId(memeberId, itemId);
		
		cartRepository.delete(cart);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
