package com.shapestone.Banking.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "accountid")
	private int accountid;
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	@Column(name = "gender")
	private String gender;
	@Column(name = "dateofjoining")
	private String dateofjoining;
	@Column(name = "openingbalance")
	private float openiningbalance;

	@OneToMany(targetEntity = Payment.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "accountid")
	@Override
	public String toString() {
		return "Account [accountid=" + accountid + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", dateofjoining=" + dateofjoining + ", openiningbalance=" + openiningbalance + "]";
	}

//	private List<Payment> payment;

}
