package com.java.business.HeadOffice.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="newbank")
public class NewBank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long newBankId;
	
	private long ownBankId;
	
	private long clientBankId;
	
	private Date date;
	
	private double amount;
	
	private String paymentType;

	public long getNewBankId() {
		return newBankId;
	}

	public void setNewBankId(long newBankId) {
		this.newBankId = newBankId;
	}

	public long getOwnBankId() {
		return ownBankId;
	}

	public void setOwnBankId(long ownBankId) {
		this.ownBankId = ownBankId;
	}

	public long getClientBankId() {
		return clientBankId;
	}

	public void setClientBankId(long clientBankId) {
		this.clientBankId = clientBankId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public NewBank() {
		super();
	}
	
	
	
}
