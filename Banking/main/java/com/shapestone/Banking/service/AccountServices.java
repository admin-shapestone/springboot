package com.shapestone.Banking.service;

import java.util.List;
import java.util.Optional;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shapestone.Banking.exception.BankingPortalException;
import com.shapestone.Banking.model.Account;
import com.shapestone.Banking.model.Payment;
import com.shapestone.Banking.repository.AccountRepository;
import com.shapestone.Banking.repository.PaymentRepository;

import lombok.extern.java.Log;
import lombok.extern.slf4j.XSlf4j;

@Service
public class AccountServices {
	org.apache.juli.logging.Log log = LogFactory.getLog(AccountServices.class);

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PaymentRepository paymentRepository;

	public List<Account> getAccountList() {
		return (List<Account>) accountRepository.findAll();
	}

	public Account validateAndSaveAccount(Account account) {
		validateAccount(account);
		return accountRepository.save(account);
	}

	private void validateAccount(Account account) {
		if (ObjectUtils.isEmpty(account)) {
			log.error("Account object is null..");
			throw new BankingPortalException("Account object is null, Please send valid data");
		}
		// TODO if there is any validation, those will go here
	}

	public Account getaccountById(int accountid) {
		if (accountid < 0) {
			throw new BankingPortalException("Account Id can't be non negative or zero");
		}
		Optional<Account> findByAccountid = accountRepository.findByAccountid(accountid);
		if (findByAccountid.isEmpty()) {
			throw new BankingPortalException("Account not found with given id");
		}
		return findByAccountid.get();
	}

	public Account updateAccount(int accountid, Account account) {
		if (accountid < 0) {
			throw new BankingPortalException("Account Id can't be non negative or zero");
		}
		Optional<Account> findByAccountid = accountRepository.findByAccountid(accountid);
		if (findByAccountid.isEmpty()) {
			throw new BankingPortalException("Account not found with given id");
		}
		Account updateAcc = findByAccountid.get();
		updateAcc.setAccountid(account.getAccountid());
		updateAcc.setName(account.getName());
		updateAcc.setAge(account.getAge());
		updateAcc.setGender(account.getGender());
		updateAcc.setDateofjoining(account.getDateofjoining());
		updateAcc.setOpeniningbalance(account.getOpeniningbalance());

		return accountRepository.save(updateAcc);
	}

	public String deleteAccByAccountId(int accountid) {
		if (accountid < 0) {
			throw new BankingPortalException("Account Id can't be non negative or zero");
		}
		Account account = accountRepository.findByAccountid(accountid)
				.orElseThrow(() -> new BankingPortalException("account"));

		List<Payment> payments = (List<Payment>) paymentRepository.findByAccountid(accountid);
		for (Payment payment : payments) {
			paymentRepository.delete(payment);
		}
		accountRepository.delete(account);
		return "account with ID deleted ";
	}

//	public String deleteAccByAccountid(int accountid) {
//		Optional<Account> findByAccountid = accountRepository.findByAccountid(accountid);
//		if (findByAccountid.isEmpty()) {
//			throw new BankingPortalException("Account not found with given id");
//		}
//		
//		accountRepository.deleteById(accountid);
//		return "Deleted emp successfully ..";
//	}
}
