package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/add")
	public String addProduct(@RequestBody Product product) {
		//TODO: process POST request
		productRepository.save(product);
		return "success";
	}

	@PostMapping("/delete")
	public String deleteProductById(@RequestParam int id) {
		//TODO: process POST request
		productRepository.deleteById(id);
		return "delete success";
	}

	@PostMapping("/update")
	public String updateProductById(@RequestParam int id, @RequestBody Product product) {
		//TODO: process POST request
		productRepository.deleteById(id);
		productRepository.save(product);
		return "delete success";
	}
	@GetMapping("/getAll")
	public List<Product> getAllProduct() {
		//TODO: process POST request
		return productRepository.findAll();
	}

	@PostMapping("/getById")
	public Optional<Product> getAllProduct(@RequestParam int id) {
		//TODO: process POST request
		return productRepository.findById(id);
	}
}
