package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Product;
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("select p from Product p where p.ISBN = ?1")
	public Product getProductByISBN(String isbn);
}
