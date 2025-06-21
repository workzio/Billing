package com.java.business.HeadOffice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java.business.HeadOffice.ZeroToNullSerializer;

@Entity
@Table(name = "receipt")
public class Receipt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long receipt_id;

	@NotNull(message = "receiptno Already Exist")
	@Column(name = "receiptno", nullable = false)
	private String receiptNo;

	@NotNull(message = "Enter the date")
	@Column(name = "date")
	private Date date;
	@JsonSerialize(using = ZeroToNullSerializer.class)
	private long distributorid;
	@JsonSerialize(using = ZeroToNullSerializer.class)
	private long companyid;

	private String receiptType;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	private boolean status;

	@NotNull(message = "received Already Exist")
	@Column(name = "received", nullable = false)
	private String received;

	@NotNull(message = "towards Already Exist")
	@Column(name = "towards", nullable = false)
	private String towards;

	@NotNull(message = "paymentType Already Exist")
	@Column(name = "paymentType", nullable = false)
	private String paymentType;

	@NotNull(message = "chequeNo Already Exist")
	@Column(name = "chequeNo", nullable = true)
	private String chequeNo;

	@NotNull(message = "bank Already Exist")
	@Column(name = "bank", nullable = true)
	private String bank;

	@NotNull(message = "amount Already Exist")
	@Column(name = "amount", nullable = false)
	private double amount;

	public long getReceipt_id() {
		return receipt_id;
	}

	public void setReceipt_id(long receipt_id) {
		this.receipt_id = receipt_id;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public String getTowards() {
		return towards;
	}

	public void setTowards(String towards) {
		this.towards = towards;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public long getDistributorid() {
		return distributorid;
	}

	public void setDistributorid(long distributorid) {
		this.distributorid = distributorid;
	}

	public long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(long companyid) {
		this.companyid = companyid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
