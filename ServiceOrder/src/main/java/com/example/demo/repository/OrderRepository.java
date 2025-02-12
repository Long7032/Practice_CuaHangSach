package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	@Query("Select o from Order o where o.userID = ?1")
	public List<Order> getListOrderByEmail(String email);
}
