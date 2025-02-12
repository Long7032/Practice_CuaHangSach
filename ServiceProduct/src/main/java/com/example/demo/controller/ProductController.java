package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
		// TODO: process POST request

		System.out.println("Data receive from request: " + product);
		productRepository.save(product);
		return "success";
	}

	@PostMapping("/delete")
	public String deleteProduct(@RequestBody Product data) {
		// TODO: process POST request
		System.out.println("Data receive from request: " + data);

		// Step 1: Find the information book by ISBN
		Product p = null;
		p = productRepository.getProductByISBN(data.getISBN());
		System.out.println("Product found in database: " + p);
		// Step 2: Delete the book by id
		productRepository.deleteById(p.getId());

		return "success";
	}

	@PostMapping("/update")
	public String updateProduct(@RequestBody Product data) {
		// TODO: process POST request
		System.out.println("Data receive from request: " + data);

		// Step 1: Find product by ISBN
		Product p = null;
		p = productRepository.getProductByISBN(data.getISBN());

		// Step 2: Delete product by id
		if (p != null) {
			productRepository.deleteById(p.getId());

			// Step 3: Save new product in database
			productRepository.save(data);

			return "success";
		}
		return "fail";
	}

	@GetMapping("/getAll")
	public List<Product> getAllProduct() {
		// TODO: process POST request
		return productRepository.findAll();
	}

	@PostMapping("/getProduct")
	public Product getProductByISBN(@RequestBody Product p) {
		// TODO: process POST request
		return productRepository.getProductByISBN(p.getISBN());
	}
}
