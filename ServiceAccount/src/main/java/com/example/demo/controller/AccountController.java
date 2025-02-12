package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/account")
public class AccountController {

	private int counter = 0;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/register")
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 3000))
	public String addAccount(@RequestBody String data) throws JsonMappingException, JsonProcessingException {
		// TODO: process POST request
		System.out.println("Data recieve from request: " + data);

		// Convert data Json to data type Map
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = objectMapper.readValue(data, Map.class);
		System.out.println("Data after convert to Json to String is: " + map);

		// Step 1: Save user info

		// Set the headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create the HttpEntity
		HttpEntity<String> entity = new HttpEntity<>(data, headers);

		// Send the request
		counter++;
		System.out.println("Try saving user information " + counter);

		String response = restTemplate.postForObject("http://localhost:5821/users/add", entity, String.class);

		System.out.println("Result of save user info is: " + response);

		// Step 2: Save account
		if (response.equalsIgnoreCase("success")) {
			Account acc = new Account(String.valueOf(map.get("email")), String.valueOf(map.get("password")));
			accountRepository.save(acc);
			counter = 0;
			return "success";
		}
		return "fail";
	}

	@PostMapping("/delete")
	public String deleteAccountById(@RequestBody Account account) {
		// TODO: process POST request
		// Step 1: Find account by account and password
		Account acc = null;
		acc = accountRepository.findAccountByAccountAndPassWord(account.getAccount(), account.getPassword());
		System.out.println("Account found in db: " + acc);

		// Step 2: delete account
		accountRepository.deleteById(acc.getId());
		return "success";
	}

	@PostMapping("/update")
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 3000))
	public String update(@RequestBody String data) throws JsonMappingException, JsonProcessingException {
		// TODO: process POST request
		System.out.println("Data recieve from request: " + data);

		// Convert data Json to data type Map
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = objectMapper.readValue(data, Map.class);
		System.out.println("Data after convert to Json to String: " + map);

		// Step 1: Find account by account and password
		Account acc = null;
		acc = accountRepository.findAccountByAccountAndPassWord(String.valueOf(map.get("email")),
				String.valueOf(map.get("password")));
		System.out.println("Account found in db: " + acc);

		// Step 2: Send the new data user to update in database
		// Set the headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create the HttpEntity
		HttpEntity<String> entity = new HttpEntity<>(data, headers);

		// Send the request
		counter++;
		System.out.println("Try saving user information " + counter);

		String response = restTemplate.postForObject("http://localhost:5821/users/update", entity, String.class);

		System.out.println("Result of save user info is: " + response);

		// Step 3: delete account
		accountRepository.deleteById(acc.getId());

		// Step 4: save new account
		Account newAccount = new Account(acc.getAccount(), String.valueOf(map.get("newPassword")));
		accountRepository.save(newAccount);

		counter = 0;
		return "success";
	}

	@GetMapping("/getAll")
	public List<Account> getAllAccount() {
		// TODO: process POST request
		return accountRepository.findAll();
	}

	@PostMapping("/login")
	@RateLimiter(name = "myRateLimiter")
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))

	public String login(@RequestBody Account account) {
		// TODO: process POST request
		System.out.println("Data recieve from request: " + account);

		Account acc = null;

		// Step 1: Find account in data base
		acc = accountRepository.findAccountByAccountAndPassWord(account.getAccount(), account.getPassword());
		System.out.println("Account found in db: " + acc);

		// Step 2: Find user information in database by email
		String userInfo = null;
		if (acc != null) {
			userInfo = restTemplate.getForObject(
					"http://localhost:5821/users/getUserByEmail?email=" + account.getAccount(), String.class);
			System.out.println("User found in db: " + userInfo);
		}
		return userInfo;
	}
}
