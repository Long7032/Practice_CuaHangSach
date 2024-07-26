package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@PostMapping("/add")
	public String createNewOrder(@RequestBody Order order) {
		// TODO: process POST request

		System.out.println("Data received from request: " + order);

		orderRepository.save(order);

		return "success";
	}
	
	@GetMapping("/getOrder")
	public List<Order> getListOrderByEmail(@RequestBody Order order) {
		// TODO: process GET request
		
		System.out.println("Data received from request: " + order);
		
		return orderRepository.getListOrderByEmail(order.getUserID());
	}
	
	@GetMapping("/getOrders")
	public List<Order> getListOrder() {
		return orderRepository.findAll();
	}
	
	

}
