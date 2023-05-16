package com.shapestone.Banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shapestone.Banking.model.Account;
import com.shapestone.Banking.service.AccountServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/accounts")
@Slf4j
@CrossOrigin(origins = "*")
public class AccountController {

	@Autowired
	private AccountServices accountService;

	@GetMapping()
	public List<Account> getAccounts() {
		log.info("In Account controller, requested {/list} web method");
		return accountService.getAccountList();
	}

	@GetMapping("/{accountid}")
	public Account getAccountById(@PathVariable("accountid") int accountid) {
		return accountService.getaccountById(accountid);

	}

	@PostMapping("/")
	public Account postAccount(@RequestBody Account account) {
		log.info("In Account controller, requested {/} post :: web method");
		return accountService.validateAndSaveAccount(account);
	}

	@PutMapping("/{accountid}")
	public Account updateAccount(@PathVariable("accountid") int accountid, @RequestBody Account account) {
		log.info("In Account controller, requested {/} put :: web method");
		return accountService.updateAccount(accountid, account);
	}

	@DeleteMapping("/{accountid}")
	public String deleteAccount(@PathVariable("accountid") int accountid) {
		log.info("In Account controller, requested {/} put :: web method");
		return accountService.deleteAccByAccountId(accountid);
	}

}
