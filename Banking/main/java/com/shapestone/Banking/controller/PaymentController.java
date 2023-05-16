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
import com.shapestone.Banking.model.Payment;
import com.shapestone.Banking.service.PaymentServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

	@Autowired
	private PaymentServices paymentService;

	@GetMapping()
	public List<Payment> getpayment() {
		log.info("In Payment controller, requested {/list} web method");
		return paymentService.getPaymentList();
	}

	@GetMapping("/{accountid}")
	public Payment getPaymentById(@PathVariable("accountid") int accountid) {
		return paymentService.getpaymentByAccountId(accountid);

	}

	@PostMapping("/")
	public Payment postPayment(@RequestBody Payment payment) {
		log.info("In Payment controller, requested {/} post :: web method");
		return paymentService.validateAndSavePayment(payment);
	}

	@PutMapping("/{accountid}")
	public Payment updatePayment(@PathVariable("accountid") int accountid, @RequestBody Payment payment) {
		log.info("In Payment controller, requested {/} put :: web method");
		return paymentService.updatePayment(accountid, payment);
	}

	@DeleteMapping("/{accountid}")
	public String deletePayment(@PathVariable("accountid") int accountid) {
		log.info("In Payment controller, requested {/} put :: web method");
		return paymentService.deletePayByAccountId(accountid);
	}

}
