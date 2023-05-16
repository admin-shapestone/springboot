package com.shapestone.Banking.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "payments")
public class Payment {

	@Column(name = "accountid")
	private int accountid;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paymentid")
	private UUID paymentid;
	@Column(name = "amountpaid")
	private double amountpaid;
	@Column(name = "amountrecived")
	private double amountrecieved;
	@Column(name = "dateofpayment")
	private String dateofpayment;
	@Column(name = "purpose")
	private String purpose;
}
