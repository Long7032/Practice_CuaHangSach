package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping(name = "account")
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/add")
	public String addAccount(@RequestBody Account Account) {
		// TODO: process POST request
		accountRepository.save(Account);
		return "add successful";
	}

	@PostMapping("/delete")
	public String deleteAccountById(@RequestParam int id) {
		// TODO: process POST request
		accountRepository.deleteById(id);
		return "delete success";
	}

	@PostMapping("/update")
	public String updateAccountById(@RequestParam int id, @RequestBody Account Account) {
		// TODO: process POST request
		accountRepository.deleteById(id);
		accountRepository.save(Account);
		return "delete success";
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
		System.out.println("Data recieve from client: " + account);
		
		Account acc = null;
		
		// Step 1: Find account in data base
		acc = accountRepository.findAccountByAccountAndPassWord(account.getAccount(), account.getPassword());
		System.out.println("Account found in db: " + acc);
	
		// Step 2: Find user information in database by email
		String userInfo = null;
		if(acc != null) {
			userInfo = restTemplate.getForObject("http://localhost:8522/users/getUserByEmail?email=" + account.getAccount(), String.class);
			System.out.println("User found in db: " + userInfo);
		}
		return userInfo;
	}
}
