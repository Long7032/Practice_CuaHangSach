package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ISBN;
	private String name;
	private String nameAuthor;
	private String namePublisher;
	private int page;

	public Product(int id, String iSBN, String name, String nameAuthor, String namePublisher, int page) {
		super();
		this.id = id;
		ISBN = iSBN;
		this.name = name;
		this.nameAuthor = nameAuthor;
		this.namePublisher = namePublisher;
		this.page = page;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameAuthor() {
		return nameAuthor;
	}
	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}
	public String getNamePublisher() {
		return namePublisher;
	}
	public void setNamePublisher(String namePublisher) {
		this.namePublisher = namePublisher;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", ISBN=" + ISBN + ", name=" + name + ", nameAuthor=" + nameAuthor
				+ ", namePublisher=" + namePublisher + ", page=" + page + "]";
	}
	
	
	

}
