package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.util.UserUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserUtil userUtil;

	@PostMapping("/add")
	public String addNewUser(@RequestBody User user) {
		System.out.println("Data recieve from  request: " + user);
		userUtil.save(user);
		return "success";
	}

	@GetMapping("/getAllUser")
	public List<User> getAllUser() {
		return userUtil.findAll();
	}

	@GetMapping("/getUserByEmail")
	public User getUserById(@RequestParam String email) {
		System.out.println("Data receive from request: " + email);
		System.out.println(userUtil.findUserByEmail(email));
		return userUtil.findUserByEmail(email);
	}

	@PostMapping("/updateUser")
	public String updateUser(@RequestParam int id, @RequestBody User user) {
		// TODO: process POST request
		userUtil.deleteById(id);
		userUtil.save(user);
		System.out.println("Update successfull");
		return "Update user successful";
	}

	@PostMapping("/deleteuser")
	public String deleteUser(@RequestParam int id) {
		// TODO: process POST request
		userUtil.deleteById(id);
		System.out.println("delete successful!!");
		return "delete user by id";
	}

}
