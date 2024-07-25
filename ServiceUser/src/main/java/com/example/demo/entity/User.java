package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "user_id", nullable = false)
	private String userId;
	@Column(name = "user_name", nullable = false)
	private String name;
	@Column(name = "user_birthday")
	private String birthday;
	@Column(name = "user_gender", nullable = false)
	private String gender;
	@Column(name = "user_email")
	private String email;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String userId, String name, String birthday, String gender, String email) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
	}
	public User(String userId, String name, String birthday, String gender, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", name=" + name + ", birthday=" + birthday + ", gender="
				+ gender + ", email=" + email + "]";
	}
	
	
}