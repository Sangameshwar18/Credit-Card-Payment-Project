package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Account;
import com.cg.service.IAccountService;

@RestController
public class AccountController {

	@Autowired
	private IAccountService accService;

	@PostMapping("/addAccount/{id}")
	public String addAccount(@PathVariable("id") long id, @RequestBody Account account) {

		return accService.addAccount(id, account);

	}

	@PatchMapping("/updateAccount/{id}")
	public String updateAccount(@PathVariable("id") long id, @RequestBody Account account) {

		return accService.updateAccount(id, account);
	}

	@GetMapping("/getAccount/{id}/{accountNumber}")
	public Account getAccount(@PathVariable("id") long id, @PathVariable("accountNumber") long accountNumber) {

		return accService.getAccount(id, accountNumber);
	}

	@GetMapping("/getAllAccounts/{id}")
	public List<Account> getAllAccounts(@PathVariable("id") long id) {

		return accService.getAllAccounts(id);
	}

	@DeleteMapping("/removeAccount/{id}/{accountNumber}")
	public String removeAccount(@PathVariable("id") int id, @PathVariable("accountNumber") long accountNumber) {

		return accService.removeAccount(id, accountNumber);
	}
}
