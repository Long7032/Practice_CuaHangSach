package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

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
	private UserRepository userRepository;

	@PostMapping("/add")
	public String addNewUser(@RequestBody User user) {
		System.out.println("Data recieve from request: " + user);
		userRepository.save(user);
		return "success";
	}

	@GetMapping("/getAllUser")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@GetMapping("/getUserByEmail")
	public User getUser(@RequestParam String data) {
		System.out.println("Data receive from request: " + data);
		System.out.println("User found in database: " + userRepository.findUserByEmail(data));
		return userRepository.findUserByEmail(data);
	}

	@PostMapping("/update")
	public String updateUser(@RequestBody String data) throws JsonMappingException, JsonProcessingException {
		// TODO: process POST request
		System.out.println("Data receive from request: " + data);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String, Object> map = objectMapper.readValue(data, Map.class);
		System.out.println("Data after convert to Json to String is: " + map);
		
		// Step 1: Find user by email
		User user = userRepository.findUserByEmail(String.valueOf(map.get("email")));
		System.out.println("User found in database: " + user);
		
		// Step 2: Delete old user by email
		userRepository.deleteById(user.getId());
		// Step 3: Save new user
		User newUser = new User(String.valueOf(map.get("name")), 
				String.valueOf(map.get("birthday")), 
				String.valueOf(map.get("gender")), 
				String.valueOf(map.get("email")));
		userRepository.save(newUser);
		
		return "success";
	}

	@PostMapping("/delete")
	public String deleteUser(@RequestParam int id) {
		// TODO: process POST request
		userRepository.deleteById(id);
		System.out.println("delete successful!!");
		return "success";
	}

}
