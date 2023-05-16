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
public class PaymentServices {
	org.apache.juli.logging.Log log = LogFactory.getLog(PaymentServices.class);

	@Autowired
	private PaymentRepository paymentRepository;

	public List<Payment> getPaymentList() {
		return (List<Payment>) paymentRepository.findAll();
	}

	public Payment validateAndSavePayment(Payment payment) {
		validatePayment(payment);
		return paymentRepository.save(payment);
	}

	private void validatePayment(Payment payment) {
		if (ObjectUtils.isEmpty(payment)) {
			log.error("payment object is null..");
			throw new BankingPortalException("payment object is null, Please send valid data");
		}
	}

	public Payment getpaymentByAccountId(int accountid) {
		if (accountid < 0) {
			throw new BankingPortalException("Account Id can't be non negative or zero");
		}
		List<Payment> findByAccountId = paymentRepository.findByAccountid(accountid);
		if (findByAccountId.isEmpty()) {
			throw new BankingPortalException("Payment not found with given id");
		}
		return paymentRepository.findByAccountid(accountid).get(accountid);
	}

	public Payment updatePayment(int accountid, Payment payment) {
		if (accountid < 0) {
			throw new BankingPortalException("payment Id can't be non negative or zero");
		}
		List<Payment> findByAccountId = paymentRepository.findByAccountid(accountid);
		if (findByAccountId.isEmpty()) {
			throw new BankingPortalException("payment not found with given id");
		}
		Payment updatepay = paymentRepository.findByAccountid(accountid).get(accountid);
		updatepay.setAccountid(payment.getAccountid());
		updatepay.setPaymentid(payment.getPaymentid());
		updatepay.setAmountpaid(payment.getAmountpaid());
		updatepay.setAmountrecieved(payment.getAmountrecieved());
		updatepay.setDateofpayment(payment.getDateofpayment());
		updatepay.setPurpose(payment.getPurpose());

		return paymentRepository.save(updatepay);
	}

	public String deletePayByAccountId(int accountid) {
		if (accountid < 0) {
			throw new BankingPortalException("Payment Id can't be non negative or zero");
		}
		List<Payment> findByAccountId = paymentRepository.findByAccountid(accountid);
		if (findByAccountId.isEmpty()) {
			throw new BankingPortalException("Payment not found with given id");
		}

		paymentRepository.deleteById(accountid);
		return "Deleted emp successfully ..";
	}
}
