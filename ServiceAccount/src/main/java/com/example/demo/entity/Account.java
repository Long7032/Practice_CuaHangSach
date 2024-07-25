package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String account;
	private String password;
	private int idUser;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String account, String password, int idUser) {
		super();
		this.account = account;
		this.password = password;
		this.idUser = idUser;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", account=" + account + ", password=" + password + ", idUser=" + idUser + "]";
	}
	
	
}
