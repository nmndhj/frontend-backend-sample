package com.example.boot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot.entity.Order;

public interface OrderRepository  extends JpaRepository<Order, Integer>{
	
	List<Order> findByMemberIdOrderByIdDesc(int memberId);

}
